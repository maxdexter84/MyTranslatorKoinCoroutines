package ru.maxdexter.mytranslatorkoincoroutines.ui.bookmarkfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.repository.Repository

class BookmarkViewModel(val repository: Repository): ViewModel() {

    val allBookmarks = repository.database.getDao().getAllBookmarks()


}