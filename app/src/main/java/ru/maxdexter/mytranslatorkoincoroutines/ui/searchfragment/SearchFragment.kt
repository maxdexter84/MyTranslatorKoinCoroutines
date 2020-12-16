package ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.ui.MainViewModel
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.SearchFragmentBinding


class SearchFragment : BottomSheetDialogFragment() {
    private var onClickListener:((String)->Unit)? = null

    fun setClickListener(listener:(String)-> Unit){
        onClickListener = listener
    }

    private val mainViewModel: MainViewModel by viewModel()
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
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        textListener()
        return binding.root
    }

    private fun textListener() {
        binding.etSearch.doAfterTextChanged { s ->
            if (s != null) {
                if (s.length >= 2) {
                    mainViewModel.getData(s.toString(),true)
                   // onClickListener?.invoke(s.toString())
                }
            }
        }
    }

}