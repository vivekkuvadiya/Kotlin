package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesInterFace {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this)
        binding.rv.adapter = adapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} is Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val note = binding.etInput.text.toString()
        if (note.isNotEmpty()) {
            viewModel.insertNote(Note(note))
            Toast.makeText(this, "$note is Inserted", Toast.LENGTH_SHORT).show()
            binding.etInput.editableText.clear()
        }
    }
}

