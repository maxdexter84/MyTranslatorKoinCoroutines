package ru.maxdexter.mytranslatorkoincoroutines.ui.historyfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.repository.Repository

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    var itemList: List<DetailModel>? = null
    private val _historyData = MutableLiveData<AppState?>(null)
            val historyData: LiveData<AppState?>
            get() = _historyData



     fun getHistoryData(){
        _historyData.value = AppState.Loading
        viewModelScope.launch {
            try {
                repository.getAllHistory().map{ it.filter { item -> item.isInHistoryList } }.collect {
                    _historyData.value = AppState.Success(it)
                    itemList = it
                }
            }catch (e: Exception){
                _historyData.value = AppState.Error(e)
            }
        }
    }


    fun deleteHistoryItem(position: Int) {
        if (itemList != null){
            val item = itemList?.get(position)
            if (item != null) {
                item.isInHistoryList = false
            }
            viewModelScope.launch {
                item?.let { repository.addHistory(it) }
            }
        }

    }

    fun addHistoryItem(position: Int){
        if (itemList != null){
            val item = itemList?.get(position)
            viewModelScope.launch {
                item?.let { repository.addHistory(it) }
            }
        }
    }
}