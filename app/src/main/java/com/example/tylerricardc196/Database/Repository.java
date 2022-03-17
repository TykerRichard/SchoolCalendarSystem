package com.example.tylerricardc196.Database;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Instructor;
import com.example.tylerricardc196.Classes.Notes;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.DAO.AssignmentDAO;
import com.example.tylerricardc196.DAO.CourseDAO;
import com.example.tylerricardc196.DAO.InstructorDAO;
import com.example.tylerricardc196.DAO.NotesDAO;
import com.example.tylerricardc196.DAO.TermDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssignmentDAO tAssignmentDAO;
    private CourseDAO tCourseDAO;
    private InstructorDAO tInstructorDAO;
    private NotesDAO tNotesDAO;
    private TermDAO tTermDAO;
    private List<Assignments> tAllAssignments;
    private List<Courses> tAllCourses;
    private List<Instructor> tAllInstructors;
    private List<Notes> tAllNotes;
    private List<Terms> tAllTerms;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);






}
