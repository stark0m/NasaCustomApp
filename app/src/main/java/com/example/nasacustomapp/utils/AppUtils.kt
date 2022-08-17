package com.example.nasacustomapp.utils

import android.content.Context
import android.widget.Toast

object AppUtils {
    fun toast(context:Context,text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}