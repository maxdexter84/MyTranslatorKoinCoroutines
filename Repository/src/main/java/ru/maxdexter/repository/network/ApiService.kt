package ru.maxdexter.repository.network



import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.maxdexter.repository.model.SearchResult

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") searchWord: String) : Deferred<List<SearchResult>>

    @GET("meanings")
    fun meaningsAsync(@Query("meanings") meanings: String) : Deferred<List<SearchResult>>
}