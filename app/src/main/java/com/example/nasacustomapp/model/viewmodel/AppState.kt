package com.example.nasacustomapp.model.viewmodel

import com.example.nasacustomapp.model.nasadto.NasaDTO
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.utils.Note

sealed class AppState{
    data class Success(val serverResponce:NasaDTO):AppState()
    data class Error (val error: Throwable):AppState()
    data class ActiveTheme(val theme: AppTheme):AppState()
    data class NoteRemovedSuccess(val position:Int):AppState()
    data class NoteAddedSuccess(val position:Int,val note: Note):AppState()
    data class NotesReceived(val noteList: MutableList<Pair<Note,Boolean>>):AppState()
    object DoNothing:AppState()
    object Loading:AppState()


}
