package com.example.tylerricardc196.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tylerricardc196.Classes.Courses;

import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repository = new Repository(getApplication());
        Terms unassignedTerm=new Terms(1,"unassigned","","");
        Courses unassignedCourse= new Courses(1,"unassigned","","","",
                "","","","",1);
        repository.insert(unassignedTerm);
        repository.insert(unassignedCourse);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.CourseTermsButton:
                startActivity(new Intent(MainActivity.this, TermUI.class));
                return true;
            case R.id.CourseAssignmentsButton:
                startActivity(new Intent(MainActivity.this, AssignmentsUI.class));
                return true;
            case R.id.TermCoursesButton:
                startActivity(new Intent(MainActivity.this, CoursesUI.class));
                return true;

        }
        return false;

    }

}



