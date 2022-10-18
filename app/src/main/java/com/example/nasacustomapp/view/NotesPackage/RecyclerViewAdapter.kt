package com.example.nasacustomapp.view.NotesPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.LongNoteLayoutBinding
import com.example.nasacustomapp.databinding.SmallNoteLayoutBinding
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class RecyclerViewAdapter(
    private var listData: MutableList<Pair<Note, Boolean>>,
    val noteAction: NoteAction
) :
    RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    fun setNoteList(newListData: MutableList<Pair<Note, Boolean>>) {
        listData = newListData
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.noteType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            NoteType.TEXT_SHORT -> {
                val binding = SmallNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
                SmallNoteViewHolder(binding)
            }
            NoteType.TEXT_LONG -> {
                val binding = LongNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
                LongNoteViewHolder(binding)
            }
            else -> {
                val binding = LongNoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
                LongNoteViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position].first)
    }

    override fun getItemCount(): Int = listData.size

    inner class SmallNoteViewHolder(val binding: SmallNoteLayoutBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(note: Note) {
            binding.textNote.text = note.noteText
            binding.descriptionNote.text = note.description
            binding.addItemImageView.setOnClickListener {
                noteAction.addNote(
                    Note(
                        "New Note Added",
                        "ANY VERY VERY LONG TEXT",
                        NoteType.TEXT_LONG
                    ), position = layoutPosition
                )
            }
            binding.removeItemImageView.setOnClickListener {
                noteAction.removeNote(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener {
                noteAction.moveUpNote(layoutPosition)
            }
            binding.moveItemDown.setOnClickListener {
                noteAction.moveDownNote(layoutPosition)
            }
        }

    }

    inner class LongNoteViewHolder(val binding: LongNoteLayoutBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(note: Note) {
            binding.textNote.text = note.noteText
            binding.descriptionNote.text = note.description
            binding.textNote.visibility =
                if (listData[layoutPosition].second) View.VISIBLE else View.GONE

            binding.arrowId.setOnClickListener {
                listData[layoutPosition] = listData[layoutPosition].let {
                    it.first to !it.second
                }
                notifyItemChanged(layoutPosition)
            }
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {
        abstract fun bind(note: Note)
        override fun onItemSelect() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.pink_dark))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        noteAction.removeNote(position)
    }
}