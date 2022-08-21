package com.example.nasacustomapp.model.viewmodel

import com.example.nasacustomapp.model.nasadto.NasaDTO
import com.example.nasacustomapp.model.theme.AppTheme
import java.lang.Error

sealed class AppState{
    data class Success(val serverResponce:NasaDTO):AppState()
    data class Error (val error: Throwable):AppState()
    data class ActiveTheme(val theme: AppTheme):AppState()
    object Loading:AppState()


}
