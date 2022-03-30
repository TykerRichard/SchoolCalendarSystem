package com.example.tylerricardc196.UI;

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

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class AssignmentsUI extends AppCompatActivity {
    List<Courses> coursesList;
    RecyclerView recyclerView;
    List<Assignments> assignmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Assignments");

        Repository repository= new Repository(getApplication());
        recyclerView=findViewById(R.id.AssignmentRecycler);
        assignmentsList= repository.getAllAssignments();
        coursesList= repository.getAllCourses();
        setAdapter();


    }
    private void setAdapter() {
        AssignmentRecyclerAdapter adapter = new AssignmentRecyclerAdapter(assignmentsList,coursesList);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_assignments_ui, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AddAssignmnet:
                startActivity(new Intent(AssignmentsUI.this, AddModifyAssignments.class));
                finish();
                return true;
        }
        return false;
    }


}