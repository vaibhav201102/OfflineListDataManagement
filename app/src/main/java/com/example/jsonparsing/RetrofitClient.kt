package com.example.jsonparsing

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BaseUrl = "https://vasundharaapps.com/"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}