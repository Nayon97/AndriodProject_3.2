package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class deleteclass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText subject;
    Button deletebutton;
    String selectday;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class);


        this.setTitle("Delete Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner coloredSpinner = findViewById(R.id.dayspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.day,R.layout.color_day_spinner);
        adapter.setDropDownViewResource(R.layout.day_dropdowm);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);

        DB = new DbHelper(this);

        subject = findViewById(R.id.subject_namedelete);
        deletebutton = findViewById(R.id.routinedeletebutton);
        DB = new DbHelper(this);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subnameTxt = subject.getText().toString();
                Boolean checkinsert = DB.deleteTodoData(subnameTxt,selectday);

                if (checkinsert==true)
                {
                    Toast.makeText(deleteclass.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(deleteclass.this, "Wrong Type", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(deleteclass.this,routine.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectday = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}