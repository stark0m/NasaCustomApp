package com.example.nasacustomapp.model.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasacustomapp.model.retrofit.PODRetrofitImpl
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.utils.APPLICATION_PREFS_THEME_FILE
import com.example.nasacustomapp.utils.APP_THEME_TAG

class NasaViewModel(application: Application) : AndroidViewModel(application), VIewModelInterface {
    private val vmLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private val retrofitPOD: PODRetrofitImpl by lazy { PODRetrofitImpl() }
    private val context = getApplication<Application>().applicationContext



    fun getObserver(): MutableLiveData<AppState> = vmLiveData
    override fun getData() {
//        vmLiveData.value = AppState.ActiveTheme(AppTheme.CUSTOM)
        Thread{
            val pictureOfTheDay = retrofitPOD.getNasaPOD()

            if (pictureOfTheDay!=null){
                vmLiveData.postValue(AppState.Success(pictureOfTheDay))
            } else
            {
                vmLiveData.postValue(AppState.Error(Throwable("что то пошло не так с ретрофитом")))
            }
        }.start()

    }

    override fun setApplicationTheme(theme: AppTheme) {
        vmLiveData.value = AppState.ActiveTheme(theme)
    }
}