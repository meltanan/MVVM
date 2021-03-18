package com.example.mvvm
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var idList = mutableListOf<String>();
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        run();

        recyclerViewId.layoutManager = LinearLayoutManager(this);
    }

    fun run() {
        val request = Request.Builder()
            .url("https://api.letsbuildthatapp.com/youtube/home_feed")
            //.url("https://api.github.com/users")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string();
                Log.d("result", "$body");
                val gson= GsonBuilder().create();
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                    runOnUiThread{
                    recyclerViewId.adapter = RecyclerAdapter(homeFeed);
                }

                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                }
            }
        })
    }

    class HomeFeed (val videos: List <Videos>)
    class Videos (val id: Int, val name: String, val link: String,val numberOfViews: Int,val channel: Channel);
    class  Channel (val name: String, val profileImageUrl: String)
}