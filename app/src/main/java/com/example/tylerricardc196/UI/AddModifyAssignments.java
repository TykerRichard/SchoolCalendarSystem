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
import android.view.MenuItem;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddModifyAssignments extends AppCompatActivity {
    private final Repository repository = new Repository(getApplication());
    private final List<Assignments> allAssignments = repository.getAllAssignments();
    private final List<Courses> allCourses = repository.getAllCourses();
    final Calendar myCalendar = Calendar.getInstance();
    int courseID;


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

        try{
            Intent intent=getIntent();
            String courseName=intent.getStringExtra("AssignmentName");
            String start=intent.getStringExtra("StartDate");
            String end = intent.getStringExtra("EndDate");
            int ID=intent.getIntExtra("AssignmentID",0);
            if(ID!=0) {
                assignmentID.setText(Integer.toString(ID));
            }
            assignmentName.setText(courseName);
            startDate.setText(start);
            endDate.setText(end);
            String type=intent.getStringExtra("Type");

            int typeLocation=assignmentTypeAdapter.getPosition(type);
            assignmentSpinner.setSelection(typeLocation);

            for(Courses current : allCourses){
                if(current.getCourseID()==intent.getIntExtra("AssignedCourse",0)){
                    String title=current.getCourseTitle();
                    int titleLocation=courseAdapter.getPosition(title);
                    courseSpinner.setSelection(titleLocation);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                List<EditText> errorChecking = new ArrayList<EditText>();
                errorChecking.add(assignmentName);
                errorChecking.add(endDate);
                errorChecking.add(endDate);
                boolean error = false;

                for (EditText current : errorChecking) {
                    String currentString = current.getText().toString();
                    if (currentString.isEmpty()) {
                        error = true;
                        current.setError("This Field cannot be blank");
                        break;
                    }
                }

                if (!error && assignmentID.getText().toString().equalsIgnoreCase("disabled")) {
                    int newID = repository.NextAssignmentID();
                    for (Courses current : allCourses) {
                        if (current.getCourseTitle().equalsIgnoreCase(courseSpinner.getSelectedItem().toString())) {
                            courseID = current.getCourseID();
                        }
                    }

                    Assignments newAssignment = new Assignments(newID, assignmentName.getText().toString(),
                            assignmentSpinner.getSelectedItem().toString(), endDate.getText().toString(),
                            startDate.getText().toString(), courseID);
                    repository.insert(newAssignment);
                    Intent intent = new Intent(AddModifyAssignments.this, AssignmentsUI.class);
                    startActivity(intent);
                    finish();
                } else if (!error) {
                    int currentID = Integer.parseInt(assignmentID.getText().toString());
                    int courseID = 0;
                    for (Courses current : allCourses) {
                        if (current.getCourseTitle().equalsIgnoreCase(courseSpinner.getSelectedItem().toString())) {
                            courseID = current.getCourseID();
                        }
                    }

                    Assignments update = new Assignments(currentID, assignmentName.getText().toString(),
                            assignmentSpinner.getSelectedItem().toString(), endDate.getText().toString(),
                            startDate.getText().toString(), courseID);
                    repository.update(update);
                    Intent intent = new Intent(AddModifyAssignments.this, AssignmentsUI.class);
                    startActivity(intent);
                    finish();

                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(AddModifyAssignments.this,AssignmentsUI.class);
                startActivity(intent);
                finish();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context=v.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                if(assignmentID.getText().toString().equalsIgnoreCase("disabled")){
                    builder.setMessage("No existing assignment selected! Return to assignment menu" +
                            " and select a assignment to delete");
                    builder.setCancelable(true);
                    builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                }else{
                    for(Assignments current : allAssignments){
                        if(current.getAssignmentID() == Integer.parseInt(assignmentID.getText().toString())){
                            builder.setMessage("Are you sure you want to delete this assignment?");
                            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    repository.delete(current);
                                    dialogInterface.cancel();
                                    Intent intent= new Intent(AddModifyAssignments.this,AssignmentsUI.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                        }
                    }
                }
                AlertDialog alert=builder.create();
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
                new DatePickerDialog(AddModifyAssignments.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(EditText date, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detailed, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SimpleDateFormat format;
        String formatDate="MM/dd/yy";
        format=new SimpleDateFormat(formatDate, Locale.US);
        Date dateStart=null;
        Date dateEnd=null;
        EditText assignmentName=findViewById(R.id.AssignmentNameField);
        EditText startDate=findViewById(R.id.AssignmentStartField);
        EditText endDate=findViewById(R.id.AssignmentEndDateField);

        switch (item.getItemId()) {

            case R.id.ShareButton:

                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,assignmentName.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Sharing This Assignment Info");
                sendIntent.setType("text/plain");
                Intent shareIntent=Intent.createChooser(sendIntent,null);
                startActivity(shareIntent);
                return true;


            case R.id.SetNotificationButton:

                try{
                    dateStart=format.parse(startDate.getText().toString());
                    dateEnd=format.parse(endDate.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger=dateStart.getTime();
                Intent intent=new Intent(AddModifyAssignments.this,NotificationReceiver.class);
                intent.putExtra("key",assignmentName.getText().toString() +" starts on " + startDate.getText().toString());
                PendingIntent sender=PendingIntent.getBroadcast(AddModifyAssignments.this,MainActivity.numAlert++,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);

                Long trigger2=dateEnd.getTime();
                Intent intent2=new Intent(AddModifyAssignments.this,NotificationReceiver.class);
                intent2.putExtra("key",assignmentName.getText().toString()+" ends on "+ endDate.getText().toString());
                PendingIntent sender2=PendingIntent.getBroadcast(AddModifyAssignments.this,MainActivity.numAlert++,intent2,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,trigger2,sender2);


                return true;


        }
        return false;
    }






}