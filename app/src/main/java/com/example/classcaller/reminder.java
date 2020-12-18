package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class reminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        this.setTitle("Reminder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}