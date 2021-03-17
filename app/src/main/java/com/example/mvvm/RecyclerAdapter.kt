package com.example.mvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private var ides: List<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemId:TextView=itemView.findViewById(R.id.textViewId);

        fun bindView(id: String, position: Int) {
            itemId.text = id
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "$position", Toast.LENGTH_SHORT).show();
                //   Log.d("demo", "hello")
            }


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.id_layout, parent, false);
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return  ides.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(ides[position], position)
//        holder.itemId.text=ides[position]
////        Log.d("demo")


    }
}