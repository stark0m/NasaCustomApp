package com.example.nasacustomapp.model.repository

import com.example.nasacustomapp.utils.Note

interface NoteList {
    fun getNoteList():List<Pair<Note,Boolean>>
}