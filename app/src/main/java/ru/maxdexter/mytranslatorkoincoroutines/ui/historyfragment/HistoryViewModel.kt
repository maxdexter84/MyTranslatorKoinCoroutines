package ru.maxdexter.mytranslatorkoincoroutines.ui.historyfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.repository.Repository

class HistoryViewModel(private val repository: Repository) : ViewModel() {

    private val _historyData = MutableLiveData<AppState?>(null)
            val historyData: LiveData<AppState?>
            get() = _historyData



     fun getHistoryData(){
        _historyData.value = AppState.Loading
        viewModelScope.launch {
            try {
                repository.getAllHistory().collect {
                    _historyData.value = AppState.Success(it)
                }
            }catch (e: Exception){
                _historyData.value = AppState.Error(e)
            }
        }
    }
}