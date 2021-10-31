package com.example.notesappviewmodel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val NoteModel by lazy { ViewModelProvider(this).get( NoteViewModel ::class.java) }

    private lateinit var RC: RecyclerView
    private lateinit var RCadapter: RVAdapter
    private lateinit var ed: EditText
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        NoteModel.getNotes().observe(this,{
            notes -> RCadapter.setNotes(notes)
        })


        ed = findViewById(R.id.ed)
        btn = findViewById(R.id.btn)

        RC = findViewById(R.id.rv)
        RCadapter = RVAdapter(this)
        RC.adapter = RCadapter
        RC.layoutManager = LinearLayoutManager(this)

        btn.setOnClickListener {

            val note1 = ed.text.toString()
            if (note1.isNotEmpty()){

                val n = Notes(0, note1)
                addNote(n)
            }
        }
    }

    fun addNote(note: Notes){

        NoteModel.addNote(note)
        Toast.makeText(applicationContext, "data saved successfully! "+ note.id, Toast.LENGTH_SHORT).show()
        ed.text.clear()
        ed.clearFocus()
    }

    fun deleteNote(note: Notes) {

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("yes", DialogInterface.OnClickListener {

                    dialog, id -> NoteModel.deleteNote(note)

            })
            .setNegativeButton("No", DialogInterface.OnClickListener {

                    dialog, id -> dialog.cancel()

            })

        val alert = dialogBuilder.create()
        alert.setTitle("Are you sure want to delete?")
        alert.show()

    }

    fun updateNote(Note1: Notes){
        val dialogBuilder = AlertDialog.Builder(this)
        val ed = EditText(this)

        val NoteId = Note1.id
        ed.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    dialog, id->
                val NewNote = ed.text.toString()
                NoteModel.updateNote(NoteId, NewNote)

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(ed)
        alert.show()
    }



}