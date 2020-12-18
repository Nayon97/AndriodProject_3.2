package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class todo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

       this.setTitle("Todo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}