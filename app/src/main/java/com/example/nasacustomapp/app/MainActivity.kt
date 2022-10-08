package com.example.nasacustomapp.app

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nasacustomapp.R
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.APPLICATION_PREFS_THEME_FILE
import com.example.nasacustomapp.utils.APP_THEME_TAG
import com.example.nasacustomapp.utils.DEFAULT_THEME
import com.example.nasacustomapp.view.NasaFragment

class MainActivity : AppCompatActivity() {

    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(this).get(NasaViewModel::class.java)
    }
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(APPLICATION_PREFS_THEME_FILE, Context.MODE_PRIVATE)
    }

    private var currentTheme:Int
        set(value) {
            sharedPreferences.edit().putInt(APP_THEME_TAG, value).apply()
        }
        get() = sharedPreferences.getInt(APP_THEME_TAG, DEFAULT_THEME)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(currentTheme)
        setContentView(R.layout.activity_main)

        viewModelNasaFragment.getObserver().observe(this){activityAction(it)}

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, NasaFragment.newInstance())
                .commitNow()
        }
    }

    private fun activityAction(appState: AppState) {

        when (appState) {

            is AppState.ActiveTheme -> {
                currentTheme = AppTheme.getTheme(appState.theme)


                recreate()
            }
            else -> {
                //nothing todo
            }
        }
    }
}