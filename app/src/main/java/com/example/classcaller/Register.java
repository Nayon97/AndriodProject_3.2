package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {


    EditText mEmailEt,mPasswordEt;
    Button mRegisterBtn;
    TextView mHaveAccountTv;
    //progress bar display while register user
    ProgressDialog progressDialog;



    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ActionBar and its Tirtle

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Create Account");
        // Anable Back Buttron
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        //int

        mEmailEt =findViewById(R.id.emailEt);
        mPasswordEt=findViewById(R.id.passwordEt);
        mRegisterBtn=findViewById(R.id.registerBtn);
        mHaveAccountTv =findViewById(R.id.have_acountTv);
        //In the onCreate() method, initialize the FirebaseAuth instance.

        mAuth = FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering User");



        //Handel regester


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input Emaill Password
                String email=mEmailEt.getText().toString().trim();
                String password=mPasswordEt.getText().toString().trim();

                //validate
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                    // set Error and Focouse To email Edit Text

                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);

                }
                else if (password.length()<6){

                    mPasswordEt.setError("Password Length Must Be 6 Charecter");
                    mPasswordEt.setFocusable(true);
                }
                else {
                    registerUser(email,password); // register the user

                }
            }


        });

        //handel  login text view click listener
        mHaveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,LoginActivity.class));
                finish();

            }
        });

    }

    private void registerUser(String email, String password) {
        //Email password pattern is valid ,show progress bar and dialog

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, dismiss dialog and starrt register actvity
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //get User Email and uid from auth

                            String email =user.getEmail();
                            String uid =user.getUid();

                            //when user is registered store user info  in fire base realtime database too
                            // using has map


                            HashMap<Object ,String>hashMap=new HashMap<>();

                            //put info in has map

                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
                            hashMap.put("name","");         //will add later (e.g edit profile)
                            hashMap.put("phone","");       //will add later (e.g edit profile)
                            hashMap.put("image","");        //will add later (e.g edit profile)

                            //fire baseDatabase instance

                            FirebaseDatabase database =FirebaseDatabase.getInstance();

                            //path to store user data named "USER"
                            DatabaseReference reference =database.getReference("Users");

                            //put data has map in data base


                            reference.child(uid).setValue(hashMap);






                            Toast.makeText(Register.this, "Registerd...\n"+user.getEmail(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //erroer dismiss progerss dialog and get and show the error massage
                Toast.makeText(Register.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();



            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go privious Activity
        return super.onSupportNavigateUp();
    }
}