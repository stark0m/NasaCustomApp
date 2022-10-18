package com.example.nasacustomapp.model.repository

import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.example.nasacustomapp.R
import com.example.nasacustomapp.utils.LONG_TEXT
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class NoteListHardCode:NoteList {
    override fun getNoteList(): List<Note> {
        val resources =
        return listOf(
            Note("first note", noteText = LONG_TEXT, noteType = NoteType.TEXT_LONG),
            Note("second note","Verylong text"),
            Note("3 note","Verylong text"),
            Note("4 note","Verylong text"),
            Note("5 note","Verylong text"),
            Note("6 note","Verylong text"),
            Note("7 note","Verylong text")
        )
    }
}