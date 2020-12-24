package ru.maxdexter.repository.repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.maxdexter.repository.db.Database
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.network.Retrofit

class Repository ( val database: Database) {


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


     fun isExistenceInTable(word: String): Flow<List<DetailModel>> {
       return database.getDao().getByWordAsync(word).flowOn(Dispatchers.IO)
    }



}
