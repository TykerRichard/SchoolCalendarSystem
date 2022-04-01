package com.example.tylerricardc196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.List;

public class TermUI extends AppCompatActivity {

    List<Terms> termsList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_ui);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Terms");

        Repository repository = new Repository(getApplication());
        recyclerView = findViewById(R.id.TermListView);
        termsList = repository.getAllTerms();
        setAdapter();


    }

    private void setAdapter() {
        TermRecyclerAdapter adapter = new TermRecyclerAdapter(termsList);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_term_u_i, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Action_AddTerm:
                startActivity(new Intent(TermUI.this, AddModifyTerm.class));
                finish();
                return true;
            case R.id.TermAssignmentsButton:
                startActivity(new Intent(TermUI.this, AssignmentsUI.class));
                finish();
                return true;
            case R.id.TermCoursesButton:
                startActivity(new Intent(TermUI.this, CoursesUI.class));
                finish();
                return true;
        }
            return false;


    }
}




