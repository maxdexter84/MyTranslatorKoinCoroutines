package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.model.DetailModel
import ru.maxdexter.repository.model.SearchResult
import ru.maxdexter.repository.repository.Repository

class SearchViewModel(private val repository: Repository) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()
    val appState: LiveData<AppState>
        get() = _appState

    private lateinit var currentWordList: MutableList<SearchResult>

    fun getData(word: String, isOnline: Boolean){
        _appState.value = AppState.Loading
        viewModelScope.launch {
            try {
                val res = repository.getTranslate(word,isOnline)
                currentWordList = res as MutableList<SearchResult>
                _appState.value = AppState.Success(res)
            }catch (e: Exception){
                _appState.value = AppState.Error(e)
            }
        }
    }


     fun saveHistoryData(detailModel: DetailModel){
         viewModelScope.launch {
             repository.addHistory(detailModel)
         }

        }





}
