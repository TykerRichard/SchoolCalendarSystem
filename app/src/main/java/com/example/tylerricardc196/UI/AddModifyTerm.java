package com.example.tylerricardc196.UI;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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

import com.example.tylerricardc196.Classes.Terms;
import com.example.tylerricardc196.Database.Repository;
import com.example.tylerricardc196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddModifyTerm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_term);

        final Calendar myCalendar = Calendar.getInstance();


        final Button save = findViewById(R.id.SaveButton);
        final Button cancel = findViewById(R.id.CancelButton);
        EditText startDateFld = findViewById(R.id.StartDateField);
        EditText endDateFld = findViewById(R.id.EndDateField);
        EditText termNameFld = findViewById(R.id.TermNameField);
        TextView termIDFld = findViewById(R.id.TermIDField);
        Repository repository = new Repository(getApplication());
        try{
            Intent intent=getIntent();
            String termName= intent.getStringExtra("TermNameField");
            String startDate=intent.getStringExtra("StartDateField");
            String endDate = intent.getStringExtra("EndDateField");
            int termID=intent.getIntExtra("TermID",0);

            termNameFld.setText(termName);
            startDateFld.setText(startDate);
            endDateFld.setText(endDate);
            if(termID!=0){
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
                String termIDString= termIDFld.getText().toString();


                boolean error = false;


                if (termName.isEmpty()) {
                    termNameFld.setError("cannot be blank");
                    error = true;
                }
                if(startDate.isEmpty()){
                    startDateFld.setError("Cannot be blank");
                    error=true;
                }
                if(endDate.isEmpty()){
                    endDateFld.setError("Cannot be blank");
                    error=true;
                }

                if (!error && termIDString.contains("Disabled")) {
                    int termID = (repository.getAllTerms().size()) + 1;
                    Terms newTerm = new Terms(termID, termName, startDate, endDate);
                    repository.insert(newTerm);
                    Intent intent=new Intent(AddModifyTerm.this,TermUI.class);
                    startActivity(intent);
                    finish();
                }else{
                    Terms updateTerm=new Terms(Integer.parseInt(termIDString),termName,startDate,endDate);
                    repository.update(updateTerm);
                    Intent intent=new Intent(AddModifyTerm.this,TermUI.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });

        startDateFld.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                datePicker(startDateFld,myCalendar);
            }
        });
        endDateFld.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                datePicker(endDateFld,myCalendar);
            }
        });


    }

    private void datePicker(EditText editText,Calendar calendar){
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
