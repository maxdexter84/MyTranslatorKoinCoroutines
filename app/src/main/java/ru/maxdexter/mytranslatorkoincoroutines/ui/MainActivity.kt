package ru.maxdexter.mytranslatorkoincoroutines.ui

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import ru.maxdexter.mytranslatorkoincoroutines.preferences.AppSettings

import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by currentScope.inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        val isDarkTheme = AppSettings(this).isDarkTheme
            if (isDarkTheme) {
                setTheme(R.style.AppThemeNight)
            } else {
                setTheme(R.style.AppTheme)
            }

        super.onCreate(savedInstanceState)
        AppSettings(this).isDarkTheme = true


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
    }



}