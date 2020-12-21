package ru.maxdexter.mytranslatorkoincoroutines.ui.historyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.HistoryAdapter
import ru.maxdexter.mytranslatorkoincoroutines.utils.parseLoadError
import ru.maxdexter.repository.model.AppState
import ru.maxdexter.repository.model.DetailModel
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }
    private lateinit var binding: HistoryFragmentBinding
    private val viewModel: HistoryViewModel by viewModel()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(object : HistoryAdapter.OnListItemClickListener {
            override fun onClick(detailModel: DetailModel) {
                findNavController().navigate(
                    HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(
                        detailModel
                    )
                )
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.history_fragment,
            container,
            false
        )

        initRecycler()
        viewModel.getHistoryData()
        dataObserve()
        addItemTouchHalper()



        return binding.root
    }

    private fun addItemTouchHalper() {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.deleteHistoryItem(position)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.historyRecycler)
    }


    private fun initRecycler() {
        binding.historyRecycler.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }

    }

    private fun dataObserve() {
        viewModel.historyData.observe(viewLifecycleOwner, {
            when (it) {
                is AppState.Loading -> parseLoadError(binding, it)
                is AppState.Success<*> -> {
                    parseLoadError(binding, it)
                    historyAdapter.submitList(it.data as List<DetailModel>)
                }
                is AppState.Error -> parseLoadError(binding, it)
            }
        })
    }


}