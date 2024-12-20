package com.example.TaskManagerApp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.TaskManagerApp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority ASC")
    fun readAllData(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY priority ASC")
    fun searchNotesByTitle(searchQuery: String): LiveData<List<Note>>

}