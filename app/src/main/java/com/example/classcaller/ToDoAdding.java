package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ToDoAdding extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView Day,Time;
    int Hour,Minute;
    EditText subject,note;
    Button submitbutton;
    String topic;

    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_adding);

        this.setTitle("Add Todo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner coloredSpinner = findViewById(R.id.coloredspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.ToDoItem,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);

        Day = findViewById(R.id.day_of_submission);
        Time = findViewById(R.id.time_of_submission);
        subject = findViewById(R.id.subject_name);
        note = findViewById(R.id.note);
        submitbutton = findViewById(R.id.todosubmitbutton);

        DB = new DbHelper(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ToDoAdding.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = day + "/"+month+"/"+year;
                        Day.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ToDoAdding.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //init hour minute

                        Hour = hourOfDay;
                        Minute = minute;
                        //initial calander

                        Calendar calendar = Calendar.getInstance();
                        //set hour and minute

                        calendar.set(0,0,0,Hour,Minute);
                        //set selected time

                        Time.setText(DateFormat.format("hh:mm aa",calendar));

                    }
                },12,0,false);

                //display
                timePickerDialog.updateTime(Hour,Minute);
                //show
                timePickerDialog.show();
            }
        });


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subnameTxt = subject.getText().toString();
                String dayTxt = Day.getText().toString();
                String timeTxt = Time.getText().toString();
                String noteTxt = note.getText().toString();

                Boolean checkinsert = DB.insertTodoData(topic,subnameTxt,dayTxt,timeTxt,noteTxt);

                if (checkinsert==true)
                {
                    Toast.makeText(ToDoAdding.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ToDoAdding.this, "No data Found", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ToDoAdding.this,todo.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         topic = parent.getSelectedItem().toString();
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}