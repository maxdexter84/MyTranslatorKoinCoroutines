package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.MainAdapter
import ru.maxdexter.mytranslatorkoincoroutines.utils.CheckNetwork
import ru.maxdexter.mytranslatorkoincoroutines.utils.parseLoadError
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.repository.model.SearchResult
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


class SearchFragment : BottomSheetDialogFragment() {
    private var mainAdapter: MainAdapter? = null
    private val searchViewModel: SearchViewModel by currentScope.inject()
    private lateinit var binding: SearchFragmentBinding

    @InternalCoroutinesApi
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
        CheckNetwork(requireContext()).observe(viewLifecycleOwner,{
            when(it){
                true -> {
                    binding.etSearch.doAfterTextChanged { s ->
                        if (s != null) {
                            if (s.length >= 2) {
                                searchViewModel.getData(s.toString(), it )
                            }
                        }
                    }
                }
                false -> Toast.makeText(requireContext(),"Нет соединения", Toast.LENGTH_LONG).show()
            }
        })

    }

    @InternalCoroutinesApi
    private fun initRecycler() {
        mainAdapter = MainAdapter(object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: SearchResult) {
                val detailModel = mapToDetailModel(data)
                val detailModelInTable = searchViewModel.isExistence(detailModel)
                if(detailModelInTable == null){
                    searchViewModel.saveHistoryData(detailModel)
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(detailModel))
                } else {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToDetailFragment(detailModelInTable)
                    )
                }
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