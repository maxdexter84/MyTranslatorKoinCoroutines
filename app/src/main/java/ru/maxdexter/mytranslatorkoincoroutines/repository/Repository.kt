package ru.maxdexter.mytranslatorkoincoroutines.repository
import android.content.Context
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.network.Retrofit

class Repository ( context: Context) {


    suspend fun getTranslate(word: String, isOnline: Boolean)= withContext(Dispatchers.IO){
            Retrofit.api.searchAsync(word).await()
        }

    }
