package com.example.tylerricardc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.tylerricardc196.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MainMenu(View view) {
        PopupMenu mainPopUpMenu = new PopupMenu(MainActivity.this, view);
        MenuInflater inflater = mainPopUpMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, mainPopUpMenu.getMenu());
        mainPopUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String selection= (String) item.getTitle();
                switch (selection) {
                    case "Assignments":
                        startActivity(new Intent(MainActivity.this, AssignmentsUI.class));
                        return true;
                    case "Courses":
                        startActivity(new Intent(MainActivity.this, CoursesUI.class));
                        return true;
                    case "Terms":
                        startActivity(new Intent(MainActivity.this, TermUI.class));
                        return true;
                    default:
                        return false;
                }
            }
        });
        mainPopUpMenu.show();
    }


}