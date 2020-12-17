package com.example.classcaller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeMenuActivity extends AppCompatActivity {


    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Home");
    }


}

