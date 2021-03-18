package com.example.mvvm

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

class GetDataFromWeb {
    private val client=OkHttpClient();

    fun run() : List <MainActivity.GitHubUser>{
        var users : List<MainActivity.GitHubUser> = arrayListOf();
        val request = Request.Builder()
            //.url("https://api.letsbuildthatapp.com/youtube/home_feed")
            .url("https://api.github.com/users")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string();
                val gson= GsonBuilder().create();
                val collectionType: Type = object : TypeToken<List<MainActivity.GitHubUser?>?>() {}.type
                //val users: List<MainActivity.GitHubUser>  = gson.fromJson(body, collectionType);
                users  = gson.fromJson(body, collectionType);
                //val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                Log.d("demo", "Im here heehehe"+ users.toString())
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                }

            }

        })
        return users
    }
}