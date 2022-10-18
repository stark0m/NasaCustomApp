package com.example.nasacustomapp.view.NotesPackage

import com.example.nasacustomapp.utils.Note

interface NoteAction {
    fun addNote(note:Note,position:Int)
    fun removeNote(position: Int)
    fun moveUpNote(position: Int)
    fun moveDownNote(position: Int)

}