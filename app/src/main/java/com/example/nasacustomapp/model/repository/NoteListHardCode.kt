package com.example.nasacustomapp.model.repository

import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.example.nasacustomapp.R
import com.example.nasacustomapp.utils.LONG_TEXT
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class NoteListHardCode : NoteList {
    val listOfNotes: MutableList<Pair<Note,Boolean>> by lazy { getHardCodeList() }

    private fun getHardCodeList(): MutableList<Pair<Note, Boolean>> = mutableListOf(
        Pair(
            Note("first note", noteText = LONG_TEXT, noteType = NoteType.TEXT_LONG),
            false
        ),
        Pair(Note("second note", "Verylong text", noteType = NoteType.TEXT_SHORT), false),
        Pair(Note("3 note", "Verylong text", noteType = NoteType.TEXT_SHORT), false),
        Pair(Note("4 note", "Verylong text", noteType = NoteType.TEXT_SHORT), false),
        Pair(Note("5 note", "Verylong text", noteType = NoteType.TEXT_SHORT), false),
        Pair(Note("6 note", "Verylong text", noteType = NoteType.TEXT_SHORT), false),
        Pair(Note("7 note", "Verylong text", noteType = NoteType.TEXT_SHORT), false)
    )
    override fun getNoteList(): MutableList<Pair<Note, Boolean>> {

            return listOfNotes
    }

    override fun addNote(note: Note, position: Int): Boolean {
        listOfNotes.add(position,Pair(note,false))
        return true
    }



    override fun removeNote(position: Int): Boolean {
        listOfNotes.removeAt(position)
        return true
    }
}