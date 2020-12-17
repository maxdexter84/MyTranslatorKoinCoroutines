package ru.maxdexter.mytranslatorkoincoroutines.ui.detailfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.maxdexter.mytranslatorkoincoroutines.extensions.loadImage
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        val args = arguments?.let { DetailFragmentArgs.fromBundle(it).detailModel }
        if (args != null){
            binding.ivImageDetail.loadImage(binding,args.imageUrl)
            binding.tvDescriptionDetail.text = args.translate
            binding.tvHeaderDetail.text = args.word
        }


        return binding.root
    }


}