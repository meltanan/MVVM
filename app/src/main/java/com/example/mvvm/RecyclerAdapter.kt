package com.example.mvvm
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


    class RecyclerAdapter (private var homeFeed: MainActivity.HomeFeed): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemId:TextView=itemView.findViewById(R.id.textViewId);

        fun bindView(id: String, position: Int) {
            itemId.text = id
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "$position", Toast.LENGTH_SHORT).show();
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.id_layout, parent, false);
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        Log.d("demo", homeFeed.videos.count().toString())
        return  homeFeed.videos.count();
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(homeFeed.videos.get(position).id.toString(),position)


    }
}