package ru.maxdexter.mytranslatorkoincoroutines.preferences

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class AppSettings(private val activity: Activity) {
    private val preferences = activity.getPreferences(AppCompatActivity.MODE_PRIVATE)
    var isDarkTheme: Boolean by PreferencesDelegate(preferences, IS_DARK_THEME,false)

    companion object{
        const val IS_DARK_THEME = "IS_DARK_THEME"
    }

}