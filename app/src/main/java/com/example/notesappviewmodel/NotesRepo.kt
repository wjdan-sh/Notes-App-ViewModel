package com.example.notesappviewmodel


import androidx.lifecycle.LiveData

class NotesRepo (private val noteDao: NotesDao) {

    val Notes: LiveData<List<Notes>> = noteDao.getAllNotes()

     fun addNote(note: Notes){
        noteDao.insertNote(note)
    }

     fun updateNote(note: Notes){
        noteDao.updateNote(note)
    }

     fun deleteNote(note: Notes){
        noteDao.deleteNote(note)
    }
}