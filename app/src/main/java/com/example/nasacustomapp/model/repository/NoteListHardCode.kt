package com.example.nasacustomapp.model.repository

import com.example.nasacustomapp.utils.Note

class NoteListHardCode:NoteList {
    override fun getNoteList(): List<Note> {
        return listOf(
            Note("first note","Verylong text"),
            Note("second note","Verylong text"),
            Note("3 note","Verylong text"),
            Note("4 note","Verylong text"),
            Note("5 note","Verylong text"),
            Note("6 note","Verylong text"),
            Note("7 note","Verylong text")
        )
    }
}