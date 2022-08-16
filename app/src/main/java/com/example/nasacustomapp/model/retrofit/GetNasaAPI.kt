package com.example.nasacustomapp.model.retrofit

import com.example.nasacustomapp.model.nasadto.NasaDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNasaAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<NasaDTO>
}