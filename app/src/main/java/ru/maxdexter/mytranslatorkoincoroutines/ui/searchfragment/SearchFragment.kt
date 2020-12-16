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
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


class SearchFragment : BottomSheetDialogFragment() {
    private var onClickListener:((String)->Unit)? = null
    private var mainAdapter: MainAdapter? = null
    fun setClickListener(listener:(String)-> Unit){
        onClickListener = listener
    }

    private val searchViewModel: SearchViewModel by viewModel()
    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
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
                   // onClickListener?.invoke(s.toString())
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
                is AppState.Success -> {
                    showViewSuccess()
                    appState.data?.let { mainAdapter?.setData(it) }
                    mainAdapter?.setItemClickListener {}
                }
                is AppState.Error -> {
                    showViewError()
                }
                is AppState.Loading -> {
                    showViewLoading()
                    binding.reloadButton.setOnClickListener {

                    }
                }
            }
        })
    }


    private fun showViewError() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.VISIBLE
    }


    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = android.view.View.VISIBLE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.VISIBLE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

}