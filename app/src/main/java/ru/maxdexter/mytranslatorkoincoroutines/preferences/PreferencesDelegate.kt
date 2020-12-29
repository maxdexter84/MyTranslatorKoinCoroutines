package ru.maxdexter.mytranslatorkoincoroutines.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferencesDelegate(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Boolean) : ReadWriteProperty<Any?, Boolean?> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean?) {
        value?.let { preferences.edit().putBoolean(name,it).apply() }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name,defaultValue)
    }


}



