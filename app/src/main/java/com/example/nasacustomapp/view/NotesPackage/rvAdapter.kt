package com.example.nasacustomapp.view.NotesPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasacustomapp.databinding.SmallNoteLayoutBinding
import com.example.nasacustomapp.utils.Note

class rvAdapter(private val listData: List<Note>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SmallNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return SmallNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SmallNoteViewHolder).bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    class SmallNoteViewHolder(val binding: SmallNoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.textNote.text = note.noteText
            binding.descriptionNote.text = note.description
        }

    }




}