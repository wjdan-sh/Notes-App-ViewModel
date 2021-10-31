package com.example.notesappviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(applicationContext: Application) : AndroidViewModel(applicationContext) {

    private val repo:NotesRepo
    private val notes : LiveData<List<Notes>>

    init {
        val notesDao = NotesDatabase.getInstance(applicationContext).NotesDao()
        repo = NotesRepo(notesDao)
        notes = repo.Notes
    }

    fun getNotes() :LiveData<List<Notes>>{
        return notes
    }

    fun addNote(note: Notes){
        CoroutineScope(Dispatchers.IO).launch {
            repo.addNote(note)
        }
    }

    fun updateNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateNote(Notes(noteID, noteText))
        }
    }

    fun deleteNote(note: Notes){
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteNote(note)
        }
    }


}


