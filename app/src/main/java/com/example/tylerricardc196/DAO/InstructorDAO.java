package com.example.tylerricardc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tylerricardc196.Classes.Instructor;

import java.util.List;


public interface InstructorDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Instructor instructor);

    @Update
    void update(Instructor instructor);

    @Delete
    void delete(Instructor instructor);

    @Query("SELECT * FROM Instructor ORDER BY instructorName ASC")
    List<Instructor> getAllInstructor();

}