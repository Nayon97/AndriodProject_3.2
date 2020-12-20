package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.Calendar;

public class addClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView start, end;
    int Hour1,Minute1,Hour2,Minute2;
    EditText coursename,coursecode,room,courseteacher;
    Button submitbutton;
    String selectday;
    DbHelper DB;

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
        DB = new DbHelper(this);

        start = findViewById(R.id.starttime);
        end = findViewById(R.id.endtime);
        coursename = findViewById(R.id.course_name);
        coursecode = findViewById(R.id.course_code);
        courseteacher = findViewById(R.id.course_teacher);
        room = findViewById(R.id.room_number);
        submitbutton = findViewById(R.id.routinesubmitbutton);

        start.setOnClickListener(new View.OnClickListener() {
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

                        start.setText(DateFormat.format("hh:mm aa",calendar));

                    }
                },12,0,false);

                //display
                timePickerDialog.updateTime(Hour1,Minute1);
                //show
                timePickerDialog.show();

            }
        });


        end.setOnClickListener(new View.OnClickListener() {
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

                        end.setText(DateFormat.format("hh:mm aa",calendar));

                    }
                },12,0,false);

                //display
                timePickerDialog.updateTime(Hour2,Minute2);
                //show
                timePickerDialog.show();

            }
        });


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coursenameTxt = coursename.getText().toString();
                String coursecodeTxt = coursecode.getText().toString();
                String roomTxt = room.getText().toString();
                String courseteacherTxt = courseteacher.getText().toString();
                String starttimeTxt = start.getText().toString();
                String endtimeTxt = end.getText().toString();

                Boolean checkinsert = DB.insertRoutineData(coursenameTxt,coursecodeTxt,roomTxt,courseteacherTxt,selectday,starttimeTxt,endtimeTxt);

                if (checkinsert==true)
                {
                    Toast.makeText(addClass.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addClass.this, "No data Found", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(addClass.this,routine.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectday = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}