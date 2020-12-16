package ru.maxdexter.mytranslatorkoincoroutines.ui.resultefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.MainAdapter
import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.ui.MainViewModel
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() = ResultFragment()
    }
    private var mainAdapter: MainAdapter? = null
    private val viewModel: ResultViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.result_fragment, container, false)
        renderData()
        initRecycler()
        return binding.root
    }


    private fun initRecycler() {
        mainAdapter = MainAdapter()
        binding.recycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun renderData() {
        mainViewModel.appState.observe(viewLifecycleOwner, { appState ->
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