package com.example.tylerricardc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tylerricardc196.Classes.Notes;

import java.util.List;


public interface NotesDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Notes note);

    @Update
    void update(Notes note);

    @Delete
    void delete(Notes note);

    @Query("SELECT * FROM Notes ORDER BY noteID  ASC")
    List<Notes> getAllNotes();

}