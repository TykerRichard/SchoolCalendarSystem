package com.example.tylerricardc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddModifyAssignments extends AppCompatActivity {
    private final Repository repository = new Repository(getApplication());
    private final List<Assignments> allAssignments = repository.getAllAssignments();
    private final List<Courses> allCourses = repository.getAllCourses();
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_assignments);
        EditText startDate = findViewById(R.id.AssignmentStartField);
        EditText endDate = findViewById(R.id.AssignmentEndDateField);
        EditText assignmentName = findViewById(R.id.AssignmentNameField);
        TextView assignmentID = findViewById(R.id.AssignmentIDField);
        Spinner assignmentSpinner = findViewById(R.id.AssignmentTypeSpinner);
        Spinner courseSpinner = findViewById(R.id.AssignmentCourseSpinner);
        List<String> courseNameList = new ArrayList<String>();
        Button saveButton = findViewById(R.id.AssignmentSaveButton);
        Button cancelButton = findViewById(R.id.AssignmentCancelButton);
        Button deleteButton = findViewById(R.id.AssignmentDeleteButton);

        for (Courses currentCourse : allCourses) {
            courseNameList.add(currentCourse.getCourseTitle());
        }


        ArrayAdapter<CharSequence> assignmentTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.assignmentTypes, android.R.layout.simple_spinner_item);
        assignmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentSpinner.setAdapter(assignmentTypeAdapter);


        ArrayAdapter<String> courseAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courseNameList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> errorChecking = new ArrayList<String>();
                errorChecking.add(assignmentName.getText().toString());
                errorChecking.add(endDate.getText().toString());
                errorChecking.add(endDate.getText().toString());
                boolean error = false;

                for (String currentString : errorChecking) {
                    if (currentString.isEmpty()) {
                        error = true;
                        break;
                    }
                }

                if (!error && assignmentID.getText().toString().equalsIgnoreCase("disabled")) {

                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });


        startDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                datePicker(startDate, myCalendar);
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePicker(endDate, myCalendar);
            }

        });

    }


    private void datePicker(EditText editText, Calendar calendar) {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel(editText, calendar);
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddModifyAssignments.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(EditText date, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }


}