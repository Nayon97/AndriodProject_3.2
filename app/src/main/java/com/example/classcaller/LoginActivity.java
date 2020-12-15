package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //views

    EditText mEmailEt,mPasswordEt;
    TextView NotHaveAccount,mRecoverPassTv;
    Button mLoginBtn;
    SignInButton mGoogleLoginBtn;

    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    // progress dialog
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login");
        // Anable Back Buttron
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //beforre mauth
        //configure google sing in


        //In the onCreate() method, initialize the FirebaseAuth instance.
        mAuth = FirebaseAuth.getInstance();


        //int
        mEmailEt =findViewById(R.id.emailEt);
        mPasswordEt=findViewById(R.id.passwordEt);
        NotHaveAccount=findViewById(R.id.nothave_acountTv);
        mLoginBtn=findViewById(R.id.login_btn);
        mRecoverPassTv=findViewById(R.id.recoverPassTv);

        //login button clivk
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // input data

                String email =mEmailEt.getText().toString();
                String password=mPasswordEt.getText().toString().trim();


                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //invailid email patrtern set error
                    mEmailEt.setError("Invaild Email");
                    mEmailEt.setFocusable(true);

                }
                else {
                    // valid Email Pttern

                    loginUser(email,password);


                }
            }
        });

        //not have account text view
        NotHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Register.class));
                finish();

            }
        });

        //recover Password Text View On click

        mRecoverPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });

        //handel google login btn click




        //init progress dialog
        progressDialog =new ProgressDialog(this);


    }

    //RecoverPassword method
    private void showRecoverPasswordDialog() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");

        //set liniar Layourt

        LinearLayout linearLayout=new LinearLayout(this);

        //views to set in dialog


        final EditText emailET=new EditText(this);
        emailET.setHint("Email");
        emailET.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        emailET.setMinEms(16);

        linearLayout.addView(emailET);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        //buttons recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //input Email
                String email = emailET.getText().toString().trim();
                beginRecovery(email);

            }
        });

        //Button Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss Dialog
                dialog.dismiss();
            }
        });

        builder.create().show();

    }

    private void beginRecovery(String email) {
        // show progress
        progressDialog.setMessage("Sending Email......");
        progressDialog.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this,"Email Sent",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Faild...",Toast.LENGTH_LONG).show();


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //get and show error massage
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    private void loginUser(String email, String passw) {
        // show progress
        progressDialog.setMessage("Logging In......");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // dismiss progress dialog
                            progressDialog.dismiss();

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,homepages.class));
                            finish();

                        } else {

                            // dismiss progress dialog
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //error , get and show error messagre
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go privious Activity
        return super.onSupportNavigateUp();
    }
}