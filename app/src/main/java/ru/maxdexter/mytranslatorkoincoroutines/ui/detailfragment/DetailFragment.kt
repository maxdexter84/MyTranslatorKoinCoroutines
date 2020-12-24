package ru.maxdexter.mytranslatorkoincoroutines.ui.detailfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.extensions.loadImage
import ru.maxdexter.repository.db.DetailModel
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private  val viewModel: DetailViewModel by currentScope.inject()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var model: DetailModel
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        val args = arguments?.let { DetailFragmentArgs.fromBundle(it).detailModel }
        if (args != null){
            model = args
            binding.ivImageDetail.loadImage(binding,model.imageUrl)
            binding.tvDescriptionDetail.text = model.translate
            binding.tvHeaderDetail.text = model.word
            if (model.bookmark) binding.imageButton.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_border_red_24,activity?.theme))
        }

        binding.imageButton.setOnClickListener {
            if (model.bookmark) {
               binding.imageButton.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_border_24,activity?.theme))
                model.bookmark = false
                viewModel.saveHistoryData(model)
            } else{
                model.bookmark = true
                binding.imageButton.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_border_red_24,activity?.theme))
                viewModel.saveHistoryData(model)
            }

        }
        return binding.root
    }


}