package ru.maxdexter.mytranslatorkoincoroutines.ui.historyfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.HistoryAdapter
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }
    private lateinit var binding: HistoryFragmentBinding
    private val viewModel: HistoryViewModel by viewModel()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.history_fragment,container, false)

        binding.historyRecycler.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        return binding.root
    }



}