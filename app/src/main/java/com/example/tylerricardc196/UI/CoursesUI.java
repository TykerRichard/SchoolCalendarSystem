package com.example.tylerricardc196.UI;

import static com.example.tylerricardc196.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class CoursesUI extends AppCompatActivity {
    List<Courses> courseList;
    RecyclerView recyclerView;
    List<Terms> termList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_courses);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Courses");

        Repository repository = new Repository(getApplication());
        recyclerView = findViewById(id.CourseView);
        courseList = repository.getAllCourses();
        termList = repository.getAllTerms();
        setAdapter();


    }

    private void setAdapter() {
        CourseRecyclerAdapter adapter = new CourseRecyclerAdapter(courseList, termList);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_course_ui, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case id.Add_Course:
                startActivity(new Intent(CoursesUI.this, AddModifyCourses.class));
                return true;
            case id.CourseAssignmentsButton:
                startActivity(new Intent(CoursesUI.this, AssignmentsUI.class));
                finish();
                return true;
            case id.CourseTermsButton:
                startActivity(new Intent(CoursesUI.this, TermUI.class));
                finish();
                return true;
        }
        return false;


    }


}