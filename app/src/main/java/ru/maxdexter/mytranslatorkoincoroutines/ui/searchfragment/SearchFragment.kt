package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.MainAdapter
import ru.maxdexter.mytranslatorkoincoroutines.utils.parseLoadError
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.model.SearchResult
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


class SearchFragment : BottomSheetDialogFragment() {
    private var mainAdapter: MainAdapter? = null
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: SearchFragmentBinding
    private var wordList: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        textListener()
        renderData()
        initRecycler()


        return binding.root
    }

    private fun mapToDetailModel(it: SearchResult): DetailModel {
        val meanings = it.meanings
        var word = " "
        var description = ""
        var imageUrl = ""
        if (it.meanings != null && it.text != null){
            word = it.text!!
            description = it.meanings!![0].translation?.translation.toString()
            imageUrl = it.meanings!![0].imageUrl ?: ""

        }
        return DetailModel(word, description, imageUrl = imageUrl)
    }

    private fun textListener() {
        binding.etSearch.doAfterTextChanged { s ->
            if (s != null) {
                if (s.length >= 2) {
                    searchViewModel.getData(s.toString(), true)
                }
            }
        }
    }

    private fun initRecycler() {
        mainAdapter = MainAdapter(object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: SearchResult) {
                val detailModel = mapToDetailModel(data)
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                        detailModel
                    )
                )
                searchViewModel.saveHistoryData(detailModel)
            }
        })
        binding.recycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun renderData() {
        searchViewModel.appState.observe(viewLifecycleOwner, { appState ->
            when (appState) {
                is AppState.Success<*> -> {
                    parseLoadError(binding, appState)
                    appState.data?.let { mainAdapter?.setData(it as List<SearchResult>)}


                }
                is AppState.Error -> {
                    parseLoadError(binding, appState)
                }
                is AppState.Loading -> {
                    parseLoadError(binding, appState)
                    binding.reloadButton.setOnClickListener {

                    }
                }
            }
        })
    }

}