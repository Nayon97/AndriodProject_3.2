package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class addClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    

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

        Button startbutton = findViewById(R.id.endtimebutton);
        Button endbutton = findViewById(R.id.endtimebutton);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });

        endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(),"Time Picker");
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