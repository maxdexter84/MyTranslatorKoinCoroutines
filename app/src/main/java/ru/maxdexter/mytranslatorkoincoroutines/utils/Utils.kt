package ru.maxdexter.mytranslatorkoincoroutines.utils

import android.view.View
import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.translatorcoincoroutine.databinding.HistoryFragmentBinding
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


fun<T> parseLoadError(binding: T,appState: AppState){
            when(appState){
                is AppState.Error -> showViewError(binding)
                is AppState.Success<*> -> showViewSuccess(binding)
                is  AppState.Loading -> showViewLoading(binding)
            }

    }


private fun <T>showViewError(binding: T) {
    when(binding){
         is HistoryFragmentBinding->{
             binding.successLinearLayout.visibility = View.GONE
             binding.loadingFrameLayout.visibility = View.GONE
             binding.errorLinearLayout.visibility = View.VISIBLE
         }
        is SearchFragmentBinding->{
            binding.successLinearLayout.visibility = View.GONE
            binding.loadingFrameLayout.visibility = View.GONE
            binding.errorLinearLayout.visibility = View.VISIBLE
        }
    }
}


private fun<T> showViewSuccess(binding: T) {
    when(binding){
        is HistoryFragmentBinding ->{
            binding.successLinearLayout.visibility = View.VISIBLE
            binding.loadingFrameLayout.visibility = View.GONE
            binding.errorLinearLayout.visibility = View.GONE
        }
        is SearchFragmentBinding -> {
            binding.successLinearLayout.visibility = View.VISIBLE
            binding.loadingFrameLayout.visibility = View.GONE
            binding.errorLinearLayout.visibility = View.GONE
        }
    }

}

private fun <T> showViewLoading(binding: T) {
   when(binding){
       is HistoryFragmentBinding -> {
           binding.successLinearLayout.visibility = View.GONE
           binding.loadingFrameLayout.visibility = View.VISIBLE
           binding.errorLinearLayout.visibility = View.GONE
       }
       is SearchFragmentBinding -> {
           binding.successLinearLayout.visibility = View.GONE
           binding.loadingFrameLayout.visibility = View.VISIBLE
           binding.errorLinearLayout.visibility = View.GONE
       }
   }
}