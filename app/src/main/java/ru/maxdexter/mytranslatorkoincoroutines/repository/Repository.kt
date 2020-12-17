package ru.maxdexter.mytranslatorkoincoroutines.repository
import android.content.Context
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maxdexter.mytranslatorkoincoroutines.db.Database
import ru.maxdexter.mytranslatorkoincoroutines.db.HistoryModel
import ru.maxdexter.mytranslatorkoincoroutines.model.DetailModel
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.network.Retrofit

class Repository ( context: Context, val database: Database) {


    suspend fun getTranslate(word: String, isOnline: Boolean)= withContext(Dispatchers.IO){
            Retrofit.api.searchAsync(word).await()
        }

    suspend fun addHistory(detailModel: DetailModel){
        withContext(Dispatchers.IO){
            database.getDao().addHistory(detailModel)
        }
    }

    suspend fun deleteHistory(detailModel: DetailModel){
        withContext(Dispatchers.IO){
            database.getDao().deleteHistory(detailModel)
        }
    }


    fun getAllHistory(): Flow<List<DetailModel>>{
        return database.getDao().getAll().flowOn(Dispatchers.IO)
    }

    }
