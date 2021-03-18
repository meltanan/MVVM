package com.example.mvvm
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    private var idList = mutableListOf<String>();
    private val client = OkHttpClient()
    val wow: GetDataFromWeb = GetDataFromWeb();
    var result = listOf<GitHubUser>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        run();
        result = wow.run();
        Log.d("demo", "Inside main "+ result.toString())


        recyclerViewId.layoutManager = LinearLayoutManager(this);
    }

    fun run() {
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
                val collectionType: Type = object : TypeToken<List<GitHubUser?>?>() {}.type
                val users: List<GitHubUser>  = gson.fromJson(body, collectionType);

                //val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                    runOnUiThread{
                    recyclerViewId.adapter = RecyclerAdapter(users);
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
    data class GitHubUser(val login: String, val id: Int, val node_id: String);
}