package com.example.nasacustomapp.model.retrofit

import com.example.nasacustomapp.BuildConfig
import com.example.nasacustomapp.utils.NASA_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PODRetrofitImpl {
    private val retrofitBuilder: GetNasaAPI by lazy {
        Retrofit.Builder()
            .baseUrl(NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(GetNasaAPI::class.java)
    }

    fun getNasaPOD() = retrofitBuilder.getPictureOfTheDay(BuildConfig.NASA_API_KEY)
        .execute()
        .takeIf { it.isSuccessful }
        ?.body()
        ?:throw IOException("Retrofit return noSuccess result")
}