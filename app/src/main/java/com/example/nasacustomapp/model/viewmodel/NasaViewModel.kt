package com.example.nasacustomapp.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasacustomapp.model.retrofit.PODRetrofitImpl

class NasaViewModel : ViewModel(), VIewModelInterface {
    private val vmLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private val retrofitPOD: PODRetrofitImpl by lazy { PODRetrofitImpl() }

    fun getObserver(): MutableLiveData<AppState> = vmLiveData
    override fun getData() {
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
}