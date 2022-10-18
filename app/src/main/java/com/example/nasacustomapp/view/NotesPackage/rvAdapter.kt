package com.example.nasacustomapp.view.NotesPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasacustomapp.databinding.LongNoteLayoutBinding
import com.example.nasacustomapp.databinding.SmallNoteLayoutBinding
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class rvAdapter(private val listData: List<Note>) :
    RecyclerView.Adapter<rvAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].noteType
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            NoteType.TEXT_SHORT -> {
                val binding = SmallNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
                SmallNoteViewHolder(binding)
            }
            else -> {
                val binding = LongNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
                LargeNoteViewHolder(binding)
            }
        }



    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    class SmallNoteViewHolder(val binding: SmallNoteLayoutBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(note: Note) {
            binding.textNote.text = note.noteText
            binding.descriptionNote.text = note.description
        }

    }

    class LargeNoteViewHolder(val binding: LongNoteLayoutBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(note: Note) {
            binding.textNote.text = note.noteText
            binding.descriptionNote.text = note.description
        }

    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(note: Note)
    }
}