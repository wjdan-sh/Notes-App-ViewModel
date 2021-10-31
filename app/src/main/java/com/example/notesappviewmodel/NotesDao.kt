package com.example.notesappviewmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes ORDER BY id ")
    fun getAllNotes(): LiveData<List<Notes>>

    @Insert
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note:Notes)




}