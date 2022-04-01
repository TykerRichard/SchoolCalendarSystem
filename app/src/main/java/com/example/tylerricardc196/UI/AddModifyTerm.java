package com.example.tylerricardc196.UI;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

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

public class AddModifyTerm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_term);

        final Calendar myCalendar = Calendar.getInstance();


        final Button save = findViewById(R.id.SaveButton);
        final Button cancel = findViewById(R.id.CancelButton);
        final Button delete = findViewById(R.id.TermDeleteButton);
        EditText startDateFld = findViewById(R.id.StartDateField);
        startDateFld.setFocusableInTouchMode(false);
        EditText endDateFld = findViewById(R.id.EndDateField);
        endDateFld.setFocusableInTouchMode(false);
        EditText termNameFld = findViewById(R.id.TermNameField);
        TextView termIDFld = findViewById(R.id.TermIDField);
        Repository repository = new Repository(getApplication());
        try {
            Intent intent = getIntent();
            String termName = intent.getStringExtra("TermNameField");
            String startDate = intent.getStringExtra("StartDateField");
            String endDate = intent.getStringExtra("EndDateField");
            int termID = intent.getIntExtra("TermID", 0);

            termNameFld.setText(termName);
            startDateFld.setText(startDate);
            endDateFld.setText(endDate);
            if (termID != 0) {
                termIDFld.setText(Integer.toString(termID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String termName = termNameFld.getText().toString();
                String startDate = startDateFld.getText().toString();
                String endDate = endDateFld.getText().toString();
                String termIDString = termIDFld.getText().toString();


                boolean error = false;


                if (termName.isEmpty()) {
                    termNameFld.setError("cannot be blank");
                    error = true;
                }
                if (startDate.isEmpty()) {
                    startDateFld.setError("Cannot be blank");
                    error = true;
                }
                if (endDate.isEmpty()) {
                    endDateFld.setError("Cannot be blank");
                    error = true;
                }

                if (!error && termIDString.equalsIgnoreCase("Disabled")) {
                    int termID = repository.NextTermID();
                    Terms newTerm = new Terms(termID, termName, startDate, endDate);
                    repository.insert(newTerm);
                    Intent intent = new Intent(AddModifyTerm.this, TermUI.class);
                    startActivity(intent);
                    finish();
                } else if (!error) {
                    Terms updateTerm = new Terms(Integer.parseInt(termIDString), termName, startDate, endDate);
                    repository.update(updateTerm);
                    Intent intent = new Intent(AddModifyTerm.this, TermUI.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddModifyTerm.this, TermUI.class);
                startActivity(intent);
                finish();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = v.getContext();
                List<Terms> allTerms = new ArrayList<Terms>();
                allTerms = repository.getAllTerms();
                if (termIDFld.getText().toString().equalsIgnoreCase("Disabled")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("No existing term selected! Return to term menu and select " +
                            " a course to delete ");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }

                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    for (Terms deleteTerm : allTerms) {
                        if (deleteTerm.getTermID() == Integer.parseInt(termIDFld.getText().toString())) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure you want to delete this term? All Courses assigned" +
                                    " to this term will be unassigned");
                            builder.setCancelable(true);

                            builder.setPositiveButton(
                                    "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            List<Courses> allCourses = repository.getAllCourses();
                                            for (Courses currentCourse : allCourses) {
                                                if (currentCourse.getTermID() == deleteTerm.getTermID()) {
                                                    currentCourse.setTermID(1);
                                                    repository.update(currentCourse);
                                                }
                                            }
                                            repository.delete(deleteTerm);
                                            dialog.cancel();
                                            Intent intent = new Intent(AddModifyTerm.this, TermUI.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                            builder.setNegativeButton(
                                    "No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }
                }

            }
        });


        startDateFld.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePicker(startDateFld, myCalendar);
            }
        });
        endDateFld.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePicker(endDateFld, myCalendar);
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
                new DatePickerDialog(AddModifyTerm.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(EditText date, Calendar calendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }


}
