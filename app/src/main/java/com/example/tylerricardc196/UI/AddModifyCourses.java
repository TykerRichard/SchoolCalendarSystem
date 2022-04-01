package com.example.tylerricardc196.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tylerricardc196.Classes.Assignments;
import com.example.tylerricardc196.Classes.Courses;
import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddModifyCourses extends AppCompatActivity {
    private final Repository repository = new Repository(getApplication());
    private final List<Terms> allTerms = repository.getAllTerms();
    private final List<Courses> allCourses = repository.getAllCourses();
    final Calendar myCalendar = Calendar.getInstance();
    private final List<Assignments> allAssignments= repository.getAllAssignments();


    public AddModifyCourses() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_courses);
        List<String> termNames = new ArrayList<String>();


        for (Terms currentTerm : allTerms) {
            termNames.add(currentTerm.getTermName());
        }


        EditText startDate = findViewById(R.id.CourseStartDateField);
        startDate.setFocusableInTouchMode(false);
        startDate.setFocusable(false);
        EditText endDate = findViewById(R.id.CourseEndDateField);
        endDate.setFocusableInTouchMode(false);
        startDate.setFocusable(false);
        Spinner statusSpinner = (Spinner) findViewById(R.id.StatusSpinner);
        Spinner termSpinner = (Spinner) findViewById(R.id.TermSpinner);

        EditText courseNameFld = findViewById(R.id.CourseNameField);
        EditText startDateFld = findViewById(R.id.CourseStartDateField);
        EditText endDateFld = findViewById(R.id.CourseEndDateField);
        EditText instructorNameFld = findViewById(R.id.InstructorNameField);
        EditText instructorPhoneNumberFld = findViewById(R.id.InstructorPhoneNumberField);
        EditText instructorEmailFld = findViewById(R.id.InstructorEmailField);
        EditText notesFld = findViewById(R.id.NotesField);
        TextView courseIDFld = findViewById(R.id.CourseIDField);
        Button cancel = findViewById(R.id.CourseCancelButton);
        Button delete = findViewById(R.id.CourseDeleteButton);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.courseStatus, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        ArrayAdapter<String> termAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, termNames);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);


        try {
            Intent intent = getIntent();
            int termID = intent.getIntExtra("TermID", 0);
            int courseID = intent.getIntExtra("CourseID", 0);
            String courseTitle = intent.getStringExtra("CourseTitle");
            String courseStart = intent.getStringExtra("CourseStart");
            String courseEnd = intent.getStringExtra("CourseEnd");
            String status = intent.getStringExtra("Status");
            String instructorName = intent.getStringExtra("InstructorName");
            String instructorNumber = intent.getStringExtra("InstructorNumber");
            String instructorEmail = intent.getStringExtra("Email");
            String notes = intent.getStringExtra("Notes");
            String termName;

            courseNameFld.setText(courseTitle);
            startDateFld.setText(courseStart);
            endDateFld.setText(courseEnd);
            instructorNameFld.setText(instructorName);
            instructorEmailFld.setText(instructorEmail);
            instructorPhoneNumberFld.setText(instructorNumber);
            notesFld.setText(notes);


            if (courseID != 0) {
                courseIDFld.setText(Integer.toString(courseID));
            }
            for (Terms currentTerm : allTerms) {
                if (currentTerm.getTermID() == termID) {
                    termName = currentTerm.getTermName();
                    int termSpinnerLocation = termAdapter.getPosition(termName);
                    termSpinner.setSelection(termSpinnerLocation);
                    break;
                }
            }

            int statusSpinnerLocation = statusAdapter.getPosition(status);
            statusSpinner.setSelection(statusSpinnerLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Button saveButton = findViewById(R.id.CourseSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String term = termSpinner.getSelectedItem().toString();
                int termID = 0;
                for (Terms currentTerm : allTerms) {
                    if (currentTerm.getTermName().equals(term)) {
                        termID = currentTerm.getTermID();
                    }
                }
                boolean error = false;

                List<EditText> errorCheck = new ArrayList<EditText>();
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
                if (!error & courseIDFld.getText().toString().equalsIgnoreCase("Disabled")) {
                    int courseID = repository.NextCourseID();
                    Courses newCourse = new Courses(courseID,
                            courseNameFld.getText().toString(), startDateFld.getText().toString(),
                            endDateFld.getText().toString(), statusSpinner.getSelectedItem().toString(),
                            instructorNameFld.getText().toString(), instructorPhoneNumberFld.getText().toString(),
                            instructorEmailFld.getText().toString(), notesFld.getText().toString(), termID);
                    repository.insert(newCourse);
                    startActivity(new Intent(AddModifyCourses.this, CoursesUI.class));
                    finish();
                } else if (!error) {
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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddModifyCourses.this, CoursesUI.class);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);


                if (courseIDFld.getText().toString().equalsIgnoreCase("disabled")) {
                    builder.setMessage("No existing course selected! Return to course menu" +
                            " and select a course to delete");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                } else {
                    for (Courses current : allCourses) {
                        if (current.getCourseID() == Integer.parseInt(courseIDFld.getText().toString())) {
                            builder.setMessage("Are you sure you want to delete this course? All assignments associated with this " +
                                    "course will be unassigned.");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    repository.delete(current);
                                    for(Assignments currentAssignment : allAssignments){
                                        if(currentAssignment.getAssignedCourse()==current.getCourseID()){
                                            currentAssignment.setAssignedCourse(1);
                                            repository.update(currentAssignment);
                                        }
                                    }
                                    dialogInterface.cancel();
                                    Intent intent = new Intent(AddModifyCourses.this, CoursesUI.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                        }
                    }
                }
                AlertDialog alert = builder.create();
                alert.show();
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
                new DatePickerDialog(AddModifyCourses.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(EditText date, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_modify_courses, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SimpleDateFormat format;
        String formatDate="MM/dd/yy";
        format=new SimpleDateFormat(formatDate, Locale.US);
        Date dateStart=null;
        Date dateEnd=null;
        EditText courseName=findViewById(R.id.CourseNameField);
        EditText startDate=findViewById(R.id.CourseStartDateField);
        EditText endDate=findViewById(R.id.CourseEndDateField);

        switch (item.getItemId()) {

            case R.id.ShareButton:

                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,courseName.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Sharing This Course Info");
                sendIntent.setType("text/plain");
                Intent shareIntent=Intent.createChooser(sendIntent,null);
                startActivity(shareIntent);
                return true;


            case R.id.SetNotificationButton: ;

                try{
                    dateStart=format.parse(startDate.getText().toString());
                    dateEnd=format.parse(endDate.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger=dateStart.getTime();
                Intent intent=new Intent(AddModifyCourses.this,NotificationReceiver.class);
                intent.putExtra("key",courseName.getText().toString() +" starts on " + startDate.getText().toString());
                PendingIntent sender=PendingIntent.getBroadcast(AddModifyCourses.this,MainActivity.numAlert++,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);

                Long trigger2=dateEnd.getTime();
                Intent intent2=new Intent(AddModifyCourses.this,NotificationReceiver.class);
                intent2.putExtra("key",courseName.getText().toString()+" ends on "+ endDate.getText().toString());
                PendingIntent sender2=PendingIntent.getBroadcast(AddModifyCourses.this,MainActivity.numAlert++,intent2,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,trigger2,sender2);


                return true;


        }
        return false;
    }



}