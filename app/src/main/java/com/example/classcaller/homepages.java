package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepages extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
   // TextView mProfileTv;
        ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepages);

        //ActionBar and its Tirtle

         actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        firebaseAuth =FirebaseAuth.getInstance();

        //init view
       // mProfileTv=findViewById(R.id.profileTv);

        //bottom navigation
        BottomNavigationView navigationView =findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        actionBar.setTitle("Home");
        HomeFragment fragment1 =new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content,fragment1,"");
        ft1.commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //handel item click
                    switch (item.getItemId()){

                        case R.id.nav_home:
                            actionBar.setTitle("Home");
                            HomeFragment fragment1 =new HomeFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content,fragment1,"");
                            ft1.commit();



                            //home fragmrent transacrion
                            return true;

                        case R.id.nav_profile:


                            actionBar.setTitle("Prorfile");
                            ProfileFragment fragment2 =new ProfileFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content,fragment2,"");
                            ft2.commit();


                            //profile fragmrent transacrion
                            return true;

                        case R.id.nav_user:

                            actionBar.setTitle("User");
                            UserFragment fragment3 =new UserFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content,fragment3,"");
                            ft3.commit();


                            //user fragmrent transacrion
                            return true;


                    }


                    return false;
                }
            };




    private void checkUserStatus(){
        //get current User
        FirebaseUser user =firebaseAuth.getCurrentUser();
        if(user !=null){

            //user is sined in satry here
            //set email of loggedin user
          //  mProfileTv.setText(user.getEmail());

        }
        else {
            // user not singed in goto main activity

            startActivity(new Intent(homepages.this,MainActivity.class));
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