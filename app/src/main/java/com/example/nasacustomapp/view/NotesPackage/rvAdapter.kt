package com.example.nasacustomapp.view.NotesPackage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasacustomapp.utils.Note

class rvAdapter(private val listData:List<Note>):RecyclerView.Adapter<RecyclerView.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int  = listData.size
}