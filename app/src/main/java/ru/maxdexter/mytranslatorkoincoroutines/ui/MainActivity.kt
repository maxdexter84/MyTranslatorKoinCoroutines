package ru.maxdexter.mytranslatorkoincoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel

import ru.maxdexter.mytranslatorkoincoroutines.adapter.MainAdapter

import ru.maxdexter.mytranslatorkoincoroutines.model.AppState
import ru.maxdexter.mytranslatorkoincoroutines.repository.Repository
import ru.maxdexter.mytranslatorkoincoroutines.ui.searchfragment.SearchFragment
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

}