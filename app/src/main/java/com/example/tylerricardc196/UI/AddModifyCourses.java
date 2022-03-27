package com.example.tylerricardc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddModifyCourses extends AppCompatActivity {
    private Repository repository=new Repository(getApplication());
    private List<Terms> allTerms=repository.getAllTerms();
    private List<Courses> allCourses=repository.getAllCourses();
    final Calendar myCalendar = Calendar.getInstance();




    public AddModifyCourses() {




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_courses);
        List<String> termNames=new ArrayList<String>();
        for(Terms currentTerm : allTerms){
            termNames.add(currentTerm.getTermName());
        }


        EditText startDate=findViewById(R.id.CourseStartDateField);
        startDate.setFocusableInTouchMode(false);
        startDate.setFocusable(false);
        EditText endDate=findViewById(R.id.CourseEndDateField);
        endDate.setFocusableInTouchMode(false);
        startDate.setFocusable(false);
        Spinner statusSpinner = (Spinner) findViewById(R.id.StatusSpinner);
        Spinner termSpinner = (Spinner) findViewById(R.id.TermSpinner);

        EditText courseNameFld=findViewById(R.id.CourseNameField);
        EditText startDateFld=findViewById(R.id.CourseStartDateField);
        EditText endDateFld= findViewById(R.id.CourseEndDateField);
        EditText instructorNameFld=findViewById(R.id.InstructorNameField);
        EditText instructorPhoneNumberFld=findViewById(R.id.InstructorPhoneNumberField);
        EditText instructorEmailFld=findViewById(R.id.InstructorEmailField);
        EditText notesFld=findViewById(R.id.NotesField);
        TextView courseIDFld=findViewById(R.id.CourseIDField);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.courseStatus, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        ArrayAdapter<String> termAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,termNames);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);



        try{
            Intent intent=getIntent();
            int termID=intent.getIntExtra("TermID",0);
            int courseID=intent.getIntExtra("CourseID",0);
            String courseTitle=intent.getStringExtra("CourseTitle");
            String courseStart=intent.getStringExtra("CourseStart");
            String courseEnd=intent.getStringExtra("CourseEnd");
            String status= intent.getStringExtra("Status");
            String instructorName=intent.getStringExtra("InstructorName");
            String instructorNumber=intent.getStringExtra("InstructorNumber");
            String instructorEmail=intent.getStringExtra("Email");
            String notes=intent.getStringExtra("Notes");
            String termName;

            courseNameFld.setText(courseTitle);
            startDateFld.setText(courseStart);
            endDateFld.setText(courseEnd);
            instructorNameFld.setText(instructorName);
            instructorEmailFld.setText(instructorEmail);
            instructorPhoneNumberFld.setText(instructorNumber);
            notesFld.setText(notes);


            if(courseID !=0){
                courseIDFld.setText(Integer.toString(courseID));
            }
            for(Terms currentTerm : allTerms) {
                if (currentTerm.getTermID() == termID) {
                    termName = currentTerm.getTermName();
                    int termSpinnerLocation=termAdapter.getPosition(termName);
                    termSpinner.setSelection(termSpinnerLocation);
                }
            }
            int statusSpinnerLocation=statusAdapter.getPosition(status);
            statusSpinner.setSelection(statusSpinnerLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }





        Button saveButton =findViewById(R.id.CourseSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String term=termSpinner.getSelectedItem().toString();
                int termID=0;
                for(Terms currentTerm : allTerms){
                    if(currentTerm.getTermName().equals(term)){
                        termID=currentTerm.getTermID();
                    }
                }
                boolean error=false;

                List<EditText> errorCheck= new ArrayList<EditText>();
                errorCheck.add(courseNameFld);
                errorCheck.add(startDateFld);
                errorCheck.add(endDateFld);
                errorCheck.add(instructorNameFld);
                errorCheck.add(instructorPhoneNumberFld);
                errorCheck.add(instructorEmailFld);
                errorCheck.add(notesFld);

                try {
                    for (EditText current : errorCheck) {
                        String currentString = current.getText().toString();
                        if (currentString.isEmpty()) {
                            error = true;
                            current.setError("This field cannot be blank");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
                if(!error & courseIDFld.getText().toString().equalsIgnoreCase("Disabled") ) {
                    int courseID = (repository.getAllTerms().size()) + 1;
                    Courses newCourse = new Courses(courseID,
                            courseNameFld.getText().toString(), startDateFld.getText().toString(),
                            endDateFld.getText().toString(), statusSpinner.getSelectedItem().toString(),
                            instructorNameFld.getText().toString(), instructorPhoneNumberFld.getText().toString(),
                            instructorEmailFld.getText().toString(), notesFld.getText().toString(), termID);
                    repository.insert(newCourse);
                    startActivity(new Intent(AddModifyCourses.this, CoursesUI.class));
                    finish();
                }
                else if(!error){
                    Courses newCourse = new Courses(Integer.parseInt(courseIDFld.getText().toString()),
                            courseNameFld.getText().toString(), startDateFld.getText().toString(),
                            endDateFld.getText().toString(), statusSpinner.getSelectedItem().toString(),
                            instructorNameFld.getText().toString(), instructorPhoneNumberFld.getText().toString(),
                            instructorEmailFld.getText().toString(), notesFld.getText().toString(), termID);
                    repository.update(newCourse);
                    startActivity(new Intent(AddModifyCourses.this, CoursesUI.class));
                    finish();
                }

            }


        });



    startDate.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
            datePicker(startDate,myCalendar);
        }
    });

    endDate.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
            datePicker(endDate,myCalendar);

        }

    });



    }







    private void datePicker(EditText editText, Calendar calendar){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel(editText,calendar);
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddModifyCourses.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel(EditText date, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }


}