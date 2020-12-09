package ru.maxdexter.mytranslatorkoincoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.repository.Repository


class MainViewModel (private val repository: Repository):ViewModel (){


    private val _appState = MutableLiveData<AppState>()
        val appState: LiveData<AppState>
        get() = _appState


    fun getData(word: String, isOnline: Boolean){
        _appState.value = AppState.Loading
        viewModelScope.launch {
            try {
                handleParseData(repository.getTranslate(word))
            }catch (e: Exception){
                _appState.value = AppState.Error(e)
            }

        }
    }

    private fun handleParseData(res:List<SearchResult>?) =
        if (res.isNullOrEmpty()) {
            _appState.value = AppState.Error(throw Exception("Данные не получены"))
        }else  {
            _appState.value = AppState.Success(res)
            }
    }





