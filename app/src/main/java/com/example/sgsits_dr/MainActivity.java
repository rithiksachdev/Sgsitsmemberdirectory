package com.example.sgsits_dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import database.DatabaseHelper;
import database.UserTable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLoginBtn;
    private EditText meditview1;
    private EditText meditview2;
    private String TAG;
    private int val;
    private boolean flag = false;
    private boolean flag1 = false;
    private FirebaseAuth mAuth;
    DatabaseHelper databaseHelper;
    SharePreferenceData sharePreferenceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
*/
        mLoginBtn = (Button) findViewById(R.id.login);
        mLoginBtn.setOnClickListener(this);
        meditview1 = (EditText) findViewById(R.id.userName);
        meditview2 = (EditText) findViewById(R.id.password);
        databaseHelper=DatabaseHelper.getInstance(this);
    }
/*

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser currentUser) {
    }
*/
    @Override
    public void onClick(View v) {
        val=  v.getId();
        switch (val) {
            case R.id.login:
                loginPerform(v);

        }

    }
    private void loginPerform(View v){
        if(meditview1.getText().toString().contentEquals("")) {
            meditview1.setError("Please enter Username");
            meditview1.requestFocus();
        }
        else if(meditview2.getText().toString().contentEquals(""))
        {
            meditview2.setError("Please enter Password");
            meditview2.requestFocus();
        }
        else
        {
            flag=true;
            flag1=true;
        }
        if (v.getId() == R.id.login && flag && flag1) {
  /*          //Toast.makeText(this,sharedPreferences.getString("key",""),Toast.LENGTH_LONG).show();
            mAuth.signInWithEmailAndPassword(meditview1.toString(), meditview2.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                             //   Toast.makeText(MainActivity.this, "Authentication failed.",
                                //        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
*/
            sharePreferenceData=new SharePreferenceData();
        try {
            Cursor cursor = databaseHelper.getDataFromUser();
            cursor.moveToFirst();
            do {
                String email = cursor.getString(3);
                String pass = cursor.getString(4);
                if (meditview1.getText().toString().equalsIgnoreCase(email) && meditview2.getText().toString().equalsIgnoreCase(pass)) {
                    sharePreferenceData.setSharedPrefString(this,"regmail",email);
                    Toast.makeText(this, "Correct Credentials", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            } while (cursor.moveToNext());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }

    }
    public void performregisteration(View v)
    {
        Intent intent = new Intent(MainActivity.this, Registeration.class);
        intent.putExtra("Data:","Hello Sgsits");
        startActivity(intent);
    }

    public void forgotpage(View view) {
        Intent intent=new Intent(this,ForgotPassword.class);
        startActivity(intent);
    }

}
