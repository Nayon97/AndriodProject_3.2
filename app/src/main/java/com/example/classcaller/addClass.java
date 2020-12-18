package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class addClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        this.setTitle("Add Classes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner coloredSpinner = findViewById(R.id.coloredspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.day,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}