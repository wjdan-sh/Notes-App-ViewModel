package com.example.notesappviewmodel

import android.graphics.Color

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(private val activity: MainActivity): RecyclerView.Adapter<RVAdapter.MessageViewHolder>() {
    private var Notes: List<Notes> = listOf()

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.MessageViewHolder {
        return RVAdapter.MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.MessageViewHolder, position: Int) {
        val Note = Notes[position]

        holder.itemView.apply {
            tvNote.text = Note.note
            if(position%2==0){llitem.setBackgroundColor(Color.parseColor("#A5D1D6"))}
            EditNote.setOnClickListener {
                activity.updateNote(Note)
            }

            DeleteNote.setOnClickListener {
                activity.deleteNote(Note)
            }
        }
    }

    override fun getItemCount() = Notes.size

    fun setNotes(Notes: List<Notes>){
        this.Notes = Notes
        notifyDataSetChanged()
    }
}


