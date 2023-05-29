package com.sametersoyoglu.kotlinnotesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sametersoyoglu.kotlinnotesapp.model.Note

@Dao
interface Notedao {
    // onConcflict -> eklenmek istenen verinin aynı anahtara sahip bir veriyle çakışması durumunda, eski veriyi yeni veriyle değiştirmesini belirtir.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote (note: Note)

    @Update
    suspend fun updateNote (note: Note)

    @Delete
    suspend fun deleteNote (note: Note)

    // id azalan sırada ayarlama
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteBody LIKE:query")
    fun searchNote(query: String?): LiveData<List<Note>>
}