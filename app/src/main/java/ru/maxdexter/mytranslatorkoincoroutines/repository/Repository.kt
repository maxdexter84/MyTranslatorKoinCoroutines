package ru.maxdexter.mytranslatorkoincoroutines.repository
import android.content.Context
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maxdexter.mytranslatorkoincoroutines.db.Database
import ru.maxdexter.mytranslatorkoincoroutines.db.HistoryModel
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.network.Retrofit

class Repository ( context: Context, val database: Database) {


    suspend fun getTranslate(word: String, isOnline: Boolean)= withContext(Dispatchers.IO){
            Retrofit.api.searchAsync(word).await()
        }

    suspend fun addHistory(historyModel: HistoryModel){
        withContext(Dispatchers.IO){
            database.getDao().addHistory(historyModel)
        }
    }

    suspend fun deleteHistory(historyModel: HistoryModel){
        withContext(Dispatchers.IO){
            database.getDao().deleteHistory(historyModel)
        }
    }


    fun getAllHistory(): Flow<List<HistoryModel>>{
        return database.getDao().getAll()
    }

    }
