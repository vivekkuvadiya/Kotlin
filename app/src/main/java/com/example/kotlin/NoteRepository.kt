package com.example.kotlin

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNOte:LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insert(note:Note){
        noteDao.insert(note)
    }

    suspend fun delete(note:Note){
        noteDao.delete(note)
    }

}