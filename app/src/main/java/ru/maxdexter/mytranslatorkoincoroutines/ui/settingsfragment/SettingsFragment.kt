package ru.maxdexter.mytranslatorkoincoroutines.ui.settingsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.maxdexter.mytranslatorkoincoroutines.preferences.AppSettings
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    private var isDarkTheme = false
    override fun onCreate(savedInstanceState: Bundle?) {
        isDarkTheme = AppSettings(requireActivity()).isDarkTheme
        super.onCreate(savedInstanceState)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container, false)

        changeTheme()
        return binding.root
    }


    private fun changeTheme() {
        binding.switchAppTheme.isChecked = isDarkTheme
        binding.switchAppTheme.setOnCheckedChangeListener { _, isChecked ->
            AppSettings(requireActivity()).isDarkTheme = isChecked
            requireActivity().recreate()
        }
    }


}