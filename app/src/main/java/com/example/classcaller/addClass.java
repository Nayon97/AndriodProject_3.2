package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class addClass extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        this.setTitle("Add Classes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}