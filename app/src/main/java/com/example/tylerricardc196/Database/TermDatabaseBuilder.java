package com.example.tylerricardc196.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.DAO.AssignmentDAO;
import com.example.tylerricardc196.DAO.CourseDAO;
import com.example.tylerricardc196.DAO.TermDAO;

@Database(entities = {Assignments.class, Courses.class, Terms.class }, version = 5,exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract AssignmentDAO assignmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (TermDatabaseBuilder.class){
                if(INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TermDatabaseBuilder.class, "prodTermDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
       return INSTANCE;
    }

}
