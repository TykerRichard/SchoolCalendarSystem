package com.example.tylerricardc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tylerricardc196.Classes.Terms;

import java.util.List;


public interface TermDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Terms term);

    @Update
    void update(Terms term);

    @Delete
    void delete(Terms term);

    @Query("SELECT * FROM Terms ORDER BY TermName  ASC")
    List<Terms> getAllTerm();

}