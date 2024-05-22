package com.example.jsonparsing

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("artwork_apps/api/AdvertiseNewApplications/17/com.hd.camera.apps.high.quality")
    fun getCategoryList() : Call<ApiResponse>
}