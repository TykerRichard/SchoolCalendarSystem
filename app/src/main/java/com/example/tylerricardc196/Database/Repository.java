package com.example.tylerricardc196.Database;

import android.app.Application;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.DAO.AssignmentDAO;
import com.example.tylerricardc196.DAO.CourseDAO;
import com.example.tylerricardc196.DAO.TermDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssignmentDAO tAssignmentDAO;
    private CourseDAO tCourseDAO;
    private TermDAO tTermDAO;
    private List<Assignments> tAllAssignments;
    private List<Courses> tAllCourses;
    private List<Terms> tAllTerms;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        tTermDAO = db.termDAO();
        tCourseDAO = db.courseDAO();
        tAssignmentDAO = db.assignmentDAO();

    }

    public List<Courses> getAllCourses() {
        databaseExecutor.execute(() -> {
            tAllCourses = tCourseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tAllCourses;

    }

    public void insert(Courses course){

        databaseExecutor.execute(()->{
            tCourseDAO.insert(course);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Courses course){
        databaseExecutor.execute(()->{
            tCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Courses course){
        databaseExecutor.execute(()->{
          tCourseDAO.delete(course);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Terms> getAllTerms() {
        databaseExecutor.execute(() -> {
            tAllTerms = tTermDAO.getAllTerm();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tAllTerms;

    }

    public void insert(Terms term){

        databaseExecutor.execute(()->{
            tTermDAO.insert(term);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Terms term){
        databaseExecutor.execute(()->{
            tTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Terms term){
        databaseExecutor.execute(()->{
            tTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Assignments> getAllAssignments() {
        databaseExecutor.execute(() -> {
            tAllAssignments = tAssignmentDAO.getAllAssignments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tAllAssignments;

    }

    public void insert(Assignments assignment){

        databaseExecutor.execute(()->{
            tAssignmentDAO.insert(assignment);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assignments assignment){
        databaseExecutor.execute(()->{
            tAssignmentDAO.update(assignment);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assignments assignment){
        databaseExecutor.execute(()->{
            tAssignmentDAO.delete(assignment);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}