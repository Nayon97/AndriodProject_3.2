package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class deleteclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class);

        this.setTitle("Delete Classe");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}