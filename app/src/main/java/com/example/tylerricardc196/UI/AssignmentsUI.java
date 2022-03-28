package com.example.tylerricardc196.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tylerricardc196.R;

public class AssignmentsUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Assignments");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_assignments_ui,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Action_AddTerm:
                startActivity(new Intent(AssignmentsUI.this, AddModifyAssignments.class));
                finish();
                return true;
        }
        return false;
    }



}