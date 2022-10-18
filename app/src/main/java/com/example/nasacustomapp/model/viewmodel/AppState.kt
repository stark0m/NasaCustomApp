package com.example.nasacustomapp.model.viewmodel

import com.example.nasacustomapp.model.nasadto.NasaDTO
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.utils.Note

sealed class AppState{
    data class Success(val serverResponce:NasaDTO):AppState()
    data class Error (val error: Throwable):AppState()
    data class ActiveTheme(val theme: AppTheme):AppState()
    data class NotesReceived(val noteList: List<Note>):AppState()
    object DoNothing:AppState()
    object Loading:AppState()


}
