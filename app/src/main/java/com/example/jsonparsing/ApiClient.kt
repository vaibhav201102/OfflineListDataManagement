package com.example.jsonparsing

object ApiClient {
    val apiService : APIService by lazy {
        RetrofitClient.retrofit.create(APIService::class.java)
    }
}