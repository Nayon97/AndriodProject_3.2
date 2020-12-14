package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView mProfileTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //ActionBar and its Tirtle

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        firebaseAuth =FirebaseAuth.getInstance();

        //init view
        mProfileTv=findViewById(R.id.profileTv);


    }

    private void checkUserStatus(){
        //get current User
        FirebaseUser user =firebaseAuth.getCurrentUser();
        if(user !=null){

            //user is sined in satry here
            //set email of loggedin user
            mProfileTv.setText(user.getEmail());


        }
        else {
            // user not singed in goto main activity

            startActivity(new Intent(homepage.this,MainActivity.class));
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
    //handelling menu item


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item id
        int id =item.getItemId();
        if(id==R.id.action_logOut){

            firebaseAuth.signOut();
            checkUserStatus();
        }

        return super.onOptionsItemSelected(item);
    }
}