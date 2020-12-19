package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class addClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView startbutton, endbutton;
    int Hour1,Minute1,Hour2,Minute2;
    EditText coursename,coursecode,room,courseteacher;
    Button submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        this.setTitle("Add Classes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner coloredSpinner = findViewById(R.id.dayspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.day,R.layout.color_day_spinner);
        adapter.setDropDownViewResource(R.layout.day_dropdowm);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);

        startbutton = findViewById(R.id.starttime);
        endbutton = findViewById(R.id.endtime);
        coursename = findViewById(R.id.course_name);
        coursecode = findViewById(R.id.course_code);
        courseteacher = findViewById(R.id.course_teacher);
        room = findViewById(R.id.room_number);
        submitbutton = findViewById(R.id.routinesubmitbutton);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //initial

                TimePickerDialog timePickerDialog = new TimePickerDialog(addClass.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //init hour minute

                        Hour1 = hourOfDay;
                        Minute1 = minute;
                        //initial calander

                        Calendar calendar = Calendar.getInstance();
                        //set hour and minute

                        calendar.set(0,0,0,Hour1,Minute1);
                        //set selected time

                        startbutton.setText(DateFormat.format("hh:mm aa",calendar));

                    }
                },12,0,false);

                //display
                timePickerDialog.updateTime(Hour1,Minute1);
                //show
                timePickerDialog.show();

            }
        });


        endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(addClass.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //init hour minute

                        Hour2 = hourOfDay;
                        Minute2 = minute;
                        //initial calander

                        Calendar calendar = Calendar.getInstance();
                        //set hour and minute

                        calendar.set(0,0,0,Hour2,Minute2);
                        //set selected time

                        endbutton.setText(DateFormat.format("hh:mm aa",calendar));

                    }
                },12,0,false);

                //display
                timePickerDialog.updateTime(Hour2,Minute2);
                //show
                timePickerDialog.show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}