package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    private var idList = mutableListOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList();

        recyclerViewId.layoutManager = LinearLayoutManager(this);
        recyclerViewId.adapter = RecyclerAdapter(idList);

    }

    private fun addToList(id: String){
        idList.add(id);
        Log.d("demo ", "here is it $idList")
    }

    private fun postToList(){
        for (i in 1..25){
            addToList("$i")

        }
    }
}