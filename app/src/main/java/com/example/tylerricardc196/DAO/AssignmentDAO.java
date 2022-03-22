package com.example.tylerricardc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tylerricardc196.Classes.Assignments;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Assignments assignment);

    @Update
    void update(Assignments assignment);

    @Delete
    void delete(Assignments assignment);

    @Query("SELECT * FROM Assignments ORDER BY assignmentName ASC")
    List<Assignments> getAllAssignments();

}
