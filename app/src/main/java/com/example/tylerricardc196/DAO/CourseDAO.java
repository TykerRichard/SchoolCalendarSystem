package com.example.tylerricardc196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tylerricardc196.Classes.Courses;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Courses courses);

    @Update
    void update(Courses courses);

    @Delete
    void delete(Courses course);

    @Query("SELECT * FROM courses ORDER BY courseID ASC")
    List<Courses> getAllCourses();

    @Query("SELECT MAX(CourseID) FROM Courses")
    int getNextCourseID();

}