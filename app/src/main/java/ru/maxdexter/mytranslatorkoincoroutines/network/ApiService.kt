package ru.maxdexter.mytranslatorkoincoroutines.network



import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") searchWord: String) : Deferred<List<SearchResult>>

    @GET("meanings")
    fun meaningsAsync(@Query("meanings") meanings: String) : Deferred<List<SearchResult>>
}