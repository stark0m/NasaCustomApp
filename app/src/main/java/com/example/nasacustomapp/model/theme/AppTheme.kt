package com.example.nasacustomapp.model.theme


import com.example.nasacustomapp.utils.CUSTOM_THEME
import com.example.nasacustomapp.utils.DEFAULT_THEME

enum class AppTheme {
    LIGHT,CUSTOM;
companion object {
    fun getTheme(theme:AppTheme):Int =
        when(theme){
            LIGHT -> DEFAULT_THEME
            CUSTOM -> CUSTOM_THEME

        }
    }

}