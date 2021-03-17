package com.example.mvvm
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        postToList();

        recyclerViewId.layoutManager = LinearLayoutManager(this);
        recyclerViewId.adapter = RecyclerAdapter(idList);




    }

    fun run() {
        val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("demo", "${e.printStackTrace()}")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    if (response.isSuccessful){
                        Log.d("demo", "response is successful")
                    }
                    else {
                        Log.d("demo", "response is NOT successful")
                    }

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
    }

    private fun addToList(id: String){
        idList.add(id);
    }

    private fun postToList(){
        for (i in 1..25){
            addToList("$i")

        }
    }
}