package com.example.nasacustomapp.model.repository

import com.example.nasacustomapp.utils.Note

interface NoteList {
    fun getNoteList():MutableList<Pair<Note,Boolean>>
    fun addNote(note:Note,position: Int = 0):Boolean
    fun removeNote(position: Int):Boolean
}