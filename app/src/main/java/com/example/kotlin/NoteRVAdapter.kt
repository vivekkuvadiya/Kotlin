package com.example.kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.databinding.ItemNoteBinding

class NoteRVAdapter(private val listener: INotesInterFace) :
    RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_note,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.binding.txt.text = allNotes[position].text
        holder.binding.btnDelete.setOnClickListener { listener.onItemClicked(allNotes[position]) }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    inner class NoteViewHolder(itemView: ItemNoteBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: ItemNoteBinding = itemView
    }

    fun updateData(newList:List<Note>){
        this.allNotes.clear()
        this.allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesInterFace {
    fun onItemClicked(note: Note)
}