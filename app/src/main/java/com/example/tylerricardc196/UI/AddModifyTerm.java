package com.example.tylerricardc196.UI;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.util.Calendar;

public class AddModifyTerm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_term);


        final Button save = findViewById(R.id.SaveButton);
        final Button cancel = findViewById(R.id.CancelButton);
        EditText startDateFld = findViewById(R.id.StartDateField);
        EditText endDateFld = findViewById(R.id.EndDateField);
        EditText termNameFld = findViewById(R.id.TermNameField);
        Repository repository = new Repository(getApplication());

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String termName = termNameFld.getText().toString();
                String startDate = startDateFld.getText().toString();
                String endDate = endDateFld.getText().toString();
                Log.d("Course Name:", termName);
                boolean error = false;

                if (termName.isEmpty()) {
                    termNameFld.setError("cannot be blank");
                    error = true;
                }

                if (!error) {

                    int termID = (repository.getAllTerms().size()) + 1;
                    Terms newTerm = new Terms(termID, termName, startDate, endDate);
                    repository.insert(newTerm);
                    finish();

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }



}
