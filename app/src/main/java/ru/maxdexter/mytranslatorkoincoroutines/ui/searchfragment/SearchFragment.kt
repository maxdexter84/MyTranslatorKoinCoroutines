package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.MainAdapter
import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.mytranslatorkoincoroutines.utils.parseLoadError
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


class SearchFragment : BottomSheetDialogFragment() {
    private var mainAdapter: MainAdapter? = null
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: SearchFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container,false)
        textListener()
        renderData()
        initRecycler()
        return binding.root
    }

    private fun textListener() {
        binding.etSearch.doAfterTextChanged { s ->
            if (s != null) {
                if (s.length >= 2) {
                    searchViewModel.getData(s.toString(),true)
                }
            }
        }
    }

    private fun initRecycler() {
        mainAdapter = MainAdapter()
        binding.recycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun renderData() {
        searchViewModel.appState.observe(viewLifecycleOwner, { appState ->
            when (appState) {
                is AppState.Success<*> -> {
                    parseLoadError(binding,appState)
                    appState.data?.let { mainAdapter?.setData(it as List<SearchResult>) }
                    mainAdapter?.setItemClickListener {}
                }
                is AppState.Error -> {
                    parseLoadError(binding,appState)
                }
                is AppState.Loading -> {
                    parseLoadError(binding,appState)
                    binding.reloadButton.setOnClickListener {

                    }
                }
            }
        })
    }
}