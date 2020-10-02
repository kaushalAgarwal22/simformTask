package com.example.simformtask.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    val interceptor = HttpLoggingInterceptor().apply {

        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {

        this.addInterceptor(interceptor)
    }.build()
    fun  getClient(baserUrl : String?):Retrofit
    {

        if(retrofit == null)
        {

           retrofit = Retrofit.Builder().baseUrl(baserUrl).client(client).addConverterFactory(
               GsonConverterFactory.create()).build()

        }
        return retrofit!!

    }
}