package ru.maxdexter.mytranslatorkoincoroutines.model


sealed class AppState {
    data class Success(val data: List<SearchResult>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
