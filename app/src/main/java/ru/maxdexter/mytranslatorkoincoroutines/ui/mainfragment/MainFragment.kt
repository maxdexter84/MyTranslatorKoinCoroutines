package ru.maxdexter.mytranslatorkoincoroutines.ui.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.maxdexter.mytranslatorkoincoroutines.ui.resultefragment.ResultFragment
import ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment.SearchFragment
import ru.maxdexter.translatorcoincoroutine.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var fragmentViewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        childFragmentManager.beginTransaction().add(R.id.fragment_container_top,SearchFragment()).commitAllowingStateLoss()
        childFragmentManager.beginTransaction().add(R.id.fragment_container_bottom,ResultFragment()).commitAllowingStateLoss()

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


}