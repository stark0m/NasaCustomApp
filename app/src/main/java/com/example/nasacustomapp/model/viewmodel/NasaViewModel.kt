package com.example.nasacustomapp.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NasaViewModel : ViewModel(),VIewModelInterface {
private val vmLiveData:MutableLiveData <AppState> = MutableLiveData<AppState>()

fun getObserver():MutableLiveData<AppState> = vmLiveData
    override fun getData() {
        TODO("Not yet implemented")
    }
}