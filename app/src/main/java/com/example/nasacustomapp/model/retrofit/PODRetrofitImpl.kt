package com.example.nasacustomapp.model.retrofit

import com.example.nasacustomapp.BuildConfig
import com.example.nasacustomapp.utils.NASA_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PODRetrofitImpl {
    private val retrofitBuilder: GetNasaAPI by lazy {
        Retrofit.Builder()
            .baseUrl(NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient(PODInterceptor()))
            .build()
            .create(GetNasaAPI::class.java)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class PODInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

    fun getNasaPOD() = retrofitBuilder.getPictureOfTheDay(BuildConfig.NASA_API_KEY)
        .execute()
        .takeIf { it.isSuccessful }
        ?.body()
        ?:throw IOException("Retrofit return noSuccess result")
}