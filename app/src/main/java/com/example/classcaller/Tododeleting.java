package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Tododeleting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tododeleting);

        this.setTitle("Delete Todo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}