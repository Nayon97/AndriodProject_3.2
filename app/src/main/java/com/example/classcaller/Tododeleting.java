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

public class Tododeleting extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText subject;
    Button deletebutton;
    String topic;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tododeleting);

        this.setTitle("Delete Todo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner coloredSpinner = findViewById(R.id.coloredspinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.ToDoItem,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);

        subject = findViewById(R.id.subject_name);
        deletebutton = findViewById(R.id.tododeletebutton);
        DB = new DbHelper(this);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subnameTxt = subject.getText().toString();

                Boolean checkinsert = DB.deleteTodoData(topic,subnameTxt);

                if (checkinsert==true)
                {
                    Toast.makeText(Tododeleting.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Tododeleting.this, "Wrong Type", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Tododeleting.this,todo.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        topic = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}