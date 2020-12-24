package ru.maxdexter.mytranslatorkoincoroutines.ui.bookmarkfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.adapter.HistoryAdapter
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentBookmarkBinding
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentDetailBinding


class BookmarkFragment : Fragment() {
    lateinit var binding: FragmentBookmarkBinding
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter(object : HistoryAdapter.OnListItemClickListener{
            override fun onClick(detailModel: DetailModel) {
                findNavController().navigate(BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(detailModel))
            }
        })
    }
    private val viewModel:BookmarkViewModel  by currentScope.inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_bookmark,container,false)


        binding.RecyclerBookmark.adapter = adapter
        binding.RecyclerBookmark.layoutManager = LinearLayoutManager(requireContext())
        viewModel.allBookmarks.observe(viewLifecycleOwner,{
            adapter.submitList(it)

        })
        return binding.root
    }

}