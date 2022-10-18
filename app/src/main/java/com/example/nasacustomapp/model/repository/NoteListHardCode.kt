package com.example.nasacustomapp.model.repository

import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.example.nasacustomapp.R
import com.example.nasacustomapp.utils.LONG_TEXT
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class NoteListHardCode : NoteList {
    override fun getNoteList(): List<Pair<Note, Boolean>> {
        val resources =
            return listOf(
                Pair(
                    Note("first note", noteText = LONG_TEXT, noteType = NoteType.TEXT_LONG),
                    false
                ),
                Pair(Note("second note", "Verylong text"), false),
                Pair(Note("3 note", "Verylong text"), false),
                Pair(Note("4 note", "Verylong text"), false),
                Pair(Note("5 note", "Verylong text"), false),
                Pair(Note("6 note", "Verylong text"), false),
                Pair(Note("7 note", "Verylong text"), false)
            )
    }
}