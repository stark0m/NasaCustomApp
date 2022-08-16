package com.example.nasacustomapp.model.viewmodel

import com.example.nasacustomapp.model.nasadto.NasaDTO
import java.lang.Error

sealed class AppState{
    data class Success(val serverResponce:NasaDTO):AppState()
    data class Error (val error: Throwable):AppState()
    object Loading:AppState()

}
