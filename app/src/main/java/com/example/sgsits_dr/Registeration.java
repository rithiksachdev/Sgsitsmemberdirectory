package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Registeration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText nm;
    private EditText email;
    private EditText pass;
    private EditText roll;
    private EditText age;
    private EditText pin;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        mAuth = FirebaseAuth.getInstance();
        nm=findViewById(R.id.txtName);
        email=findViewById(R.id.txtEmail);
        pass=findViewById(R.id.txtPwd);
        roll=findViewById(R.id.Rollno);
        age=findViewById(R.id.age);
        pin=findViewById(R.id.pinc);
        radioSexGroup=findViewById(R.id.radiosex);
        try{
            String data= (String) getIntent().getStringExtra("Data:");
            Toast.makeText(this,data,Toast.LENGTH_LONG).show();
            Log.i(TAG, data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void Registeruser(View v)
    {
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);

        if(nm.getText().toString().contentEquals("")) {
            nm.setError("Please enter your Name");
            nm.requestFocus();
        }

        else if(email.getText().toString().contentEquals("")) {
            email.setError("Please enter your Name");
        }

        else if(pass.getText().toString().contentEquals("")) {
            pass.setError("Please enter your Name");
        }

        else if(roll.getText().toString().contentEquals("")) {
            roll.setError("Please enter your Name");
        }

        else if(age.getText().toString().contentEquals("")) {
            age.setError("Please enter your Name");
        }

        else if(pin.getText().toString().contentEquals("")) {
            pin.setError("Please enter your Name");
        }

        else if(radioSexButton.getText().toString().contentEquals(""))
        {
            radioSexButton.setError("Enter Your Gender");
        }
        else
        {
            Intent intent=new Intent(Registeration.this,HomeActivity.class);
            startActivity(intent);
        }
        }
    public void gotologin(View view)
    {
        Intent intent=new Intent(Registeration.this,MainActivity.class);
        startActivity(intent);
    }
}
