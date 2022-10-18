package com.example.nasacustomapp.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.nasacustomapp.model.repository.NoteList
import com.example.nasacustomapp.model.repository.NoteListHardCode
import com.example.nasacustomapp.model.retrofit.PODRetrofitImpl
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.utils.Note
import com.example.nasacustomapp.utils.NoteType

class NasaViewModel(application: Application) : AndroidViewModel(application), VIewModelInterface {
    private val vmLiveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
    private val retrofitPOD: PODRetrofitImpl by lazy { PODRetrofitImpl() }
    private val context = getApplication<Application>().applicationContext
    private val repository: NoteList by lazy { NoteListHardCode() }
    private val listOfNotes: MutableList<Pair<Note, Boolean>> by lazy { repository.getNoteList() }

    fun getObserver(): MutableLiveData<AppState> = vmLiveData
    override fun getData() {
        Thread {
            val pictureOfTheDay = retrofitPOD.getNasaPOD()

            if (pictureOfTheDay != null) {
                vmLiveData.postValue(AppState.Success(pictureOfTheDay))
            } else {
                vmLiveData.postValue(AppState.Error(Throwable("что то пошло не так с ретрофитом")))
            }
        }.start()

    }

    fun getNotesFromVM() = listOfNotes
    fun getNotesFromServer() {
        vmLiveData.value = AppState.NotesReceived(getNotesFromVM())
    }

    override fun setApplicationTheme(theme: AppTheme) {
        vmLiveData.value = AppState.ActiveTheme(theme)
        vmLiveData.value = AppState.DoNothing
    }

    fun addNote(position: Int, note: Note) {
        if (repository.addNote(note, position)) {
//            listOfNotes.add(position, Pair(note, false))
            vmLiveData.value = AppState.NoteAddedSuccess(position, note)
        }

    }
}