package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitActivityPra : AppCompatActivity() {
    val BASE_URL = "https://cat-fact.herokuapp.com";
    private var TAG = "RetroFitActivity";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retro_fit_pra)

        getCurrentData()
    }

    private fun getCurrentData() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequests::class.java);

        GlobalScope.launch (Dispatchers.IO) {
            val response = api.getCatFacts().awaitResponse()
            if (response.isSuccessful){
                var data = response.body()!!;
                Log.d("demo","Result " + data)
            }
        }
    }
}