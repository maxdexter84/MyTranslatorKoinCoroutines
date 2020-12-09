package ru.maxdexter.mytranslatorkoincoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.repository.Repository


class MainViewModel (val repository: Repository):ViewModel (){ //Чтобы применить анотацию к конструктору надо явно указать ключевое слово constructor


    private val _appState = MutableLiveData<AppState>()
        val appState: LiveData<AppState>
        get() = _appState


    fun getData(word: String, isOnline: Boolean){
        viewModelScope.launch {
          val res =  repository.getTranslate(word)

        }


    }


    override fun onCleared() {
        super.onCleared()

    }


}