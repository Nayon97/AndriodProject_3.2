package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepages extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth firebaseAuth;
    // TextView mProfileTv;
    ActionBar actionBar;
    private CardView routine,reminder,todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepages);

        //ActionBar and its Tirtle

        actionBar = getSupportActionBar();
        actionBar.setTitle("Class Caller");

        firebaseAuth = FirebaseAuth.getInstance();
        routine = findViewById(R.id.routine);
        todo = findViewById(R.id.todo);
        reminder = findViewById(R.id.reminder);

        //adding listener

        routine.setOnClickListener(this);
        todo.setOnClickListener(this);
        reminder.setOnClickListener(this);


    }


    private void checkUserStatus() {
        //get current User
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {

            //user is sined in satry here
            //set email of loggedin user
            //  mProfileTv.setText(user.getEmail());

        } else {
            // user not singed in goto main activity

            startActivity(new Intent(homepages.this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        //check on start off
        checkUserStatus();
        super.onStart();

    }
    //infalt option menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //imflating menu

        getMenuInflater().inflate(R.menu.nav_menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId())
        {
            case R.id.routine:
                intent = new Intent(this,routine.class);
                startActivity(intent);
                break;
            case R.id.todo:
                intent = new Intent(this,todo.class);
                startActivity(intent);
                break;
            case R.id.reminder:
                intent = new Intent(this,reminder.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }





}