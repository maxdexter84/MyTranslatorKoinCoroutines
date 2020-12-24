package ru.maxdexter.mytranslatorkoincoroutines.ui.detailfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.repository.Repository

class DetailViewModel(private val repository: Repository): ViewModel() {

    fun saveHistoryData(detailModel: DetailModel){
        viewModelScope.launch {
            repository.addHistory(detailModel)
        }

    }
}