package com.example.mvvm

import com.example.mvvm.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface APIRequests {

    @GET("/facts/random")
    fun getCatFacts(): Call<CatJson>
}