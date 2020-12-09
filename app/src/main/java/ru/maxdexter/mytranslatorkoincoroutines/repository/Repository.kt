package ru.maxdexter.mytranslatorkoincoroutines.repository
import android.content.Context
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.network.Retrofit

class Repository ( context: Context) {


    suspend fun getTranslate(word: String)= withContext(Dispatchers.IO){
            Retrofit.api.searchAsync(word).await()
        }

    }
