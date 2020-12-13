package com.example.classcaller;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_home);

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

            startActivity(new Intent(HomeActivity.this,homepage.class));
            finish();


        }
        else {
            // user not singed in goto main activity

            startActivity(new Intent(HomeActivity.this,MainActivity.class));
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

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
=======
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerId);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
>>>>>>> parent of ee0988a... Home Page Changed
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}