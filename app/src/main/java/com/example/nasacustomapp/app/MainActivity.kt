package com.example.nasacustomapp.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasacustomapp.R
import com.example.nasacustomapp.view.startfragment.NasaFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NasaFragment.newInstance())
                .commitNow()
        }
    }
}