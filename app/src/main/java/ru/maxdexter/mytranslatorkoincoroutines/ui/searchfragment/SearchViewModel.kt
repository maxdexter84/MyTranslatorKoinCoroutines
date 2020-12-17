package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.maxdexter.mytranslatorkoincoroutines.db.HistoryModel
import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.repository.Repository

class SearchViewModel(private val repository: Repository) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()
    val appState: LiveData<AppState>
        get() = _appState

    fun getData(word: String, isOnline: Boolean){
        _appState.value = AppState.Loading
        viewModelScope.launch {
            try {
                delay(1000)
                handleParseData(repository.getTranslate(word,isOnline))
            }catch (e: Exception){
                _appState.value = AppState.Error(e)
            }
        }
    }

    private fun handleParseData(res:List<SearchResult>?){
        _appState.value = AppState.Success(res)
        viewModelScope.launch {
            if (res != null)
            saveHistoryData(res)
        }
    }

    private suspend fun saveHistoryData(res: List<SearchResult>){
        flow {
            emit(res)
        }.flowOn(Dispatchers.IO).collectLatest {
            delay(1000)
            val query = res[0].text
            val translate = res[0].meanings?.get(0)?.translation?.translation
            if (query != null && translate != null)
            repository.addHistory(HistoryModel(query,translate))
        }
    }

}
