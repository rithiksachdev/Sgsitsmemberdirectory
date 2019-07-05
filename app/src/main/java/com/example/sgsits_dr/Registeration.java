package com.example.sgsits_dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import database.DatabaseHelper;
import database.UserTable;

public class Registeration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FirebaseAuth mAuth;
    private EditText fname;
    private EditText lname;
    private AutoCompleteTextView branch;
    private EditText email;
    private EditText pass;
    private EditText rollno;
    private EditText contactno;
    private EditText pin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AutoCompleteTextView autoCompleteTextView;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private RadioButton rb;
    DatabaseHelper databaseHelper;
    private TextView dateText;
    private String TAG;
    private String[] city=new String[]{
            "Indore","Bhopal","Mumbai","Delhi","Jaipur","Banglore"

    };
    private String[] branchs= new String[]{
            "Computer Science Engineering","Electrical Engineering","Biomedical Engineering","Information Technology Engineering"
            ,"Electronics and Instrumentation Engineering","Mechanical Engineering","Industrial Production Engineering"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        mAuth = FirebaseAuth.getInstance();
        fname=findViewById(R.id.txtName);
        lname=findViewById(R.id.lastname);
        branch=findViewById(R.id.branch);
        email=findViewById(R.id.txtEmail);
        pass=findViewById(R.id.txtPwd);
        rollno=findViewById(R.id.Rollno);
        rb=findViewById(R.id.male);
        contactno=findViewById(R.id.contactno);
        autoCompleteTextView=findViewById(R.id.cityreg);
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
        dateText = findViewById(R.id.dob);
        findViewById(R.id.calendericon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        autoCompleteTextView.setAdapter(adapter);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,branchs);
        branch.setAdapter(adapter1);
        databaseHelper=DatabaseHelper.getInstance(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }



    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,  this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }






    public void Registeruser(View v)
    {
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);
        if(fname.getText().toString().contentEquals("")) {
            fname.setError("Please enter your First Name");
            fname.requestFocus();
        }
        else if(lname.getText().toString().contentEquals("")) {
            lname.setError("Please enter your Last Name");
            lname.requestFocus();
        }
        else if(branch.getText().toString().contentEquals("")) {
            branch.setError("Please enter your Branch");
            branch.requestFocus();
        }
        else if(email.getText().toString().contentEquals("")) {
            email.setError("Please enter your Email");
            email.requestFocus();
        }
        else if(pass.getText().toString().contentEquals("")) {
            pass.setError("Please enter your Password");
            pass.requestFocus();
        }
        else if(rollno.getText().toString().contentEquals("")) {
            rollno.setError("Please enter your Roll-No");
            rollno.requestFocus();
        }
        else if(contactno.getText().toString().contentEquals("")) {
            contactno.setError("Please enter your Contact-No");
            contactno.requestFocus();
        }
        else if(autoCompleteTextView.getText().toString().contentEquals("")) {
            autoCompleteTextView.setError("Please enter your City ");
        }
        else if(pin.getText().toString().contentEquals("")) {
            pin.setError("Please enter your Pincode");
        }
        else if(dateText.getText().toString().contentEquals("")) {
            dateText.setError("Please enter your Date of Birth");
        }
        else if(selectedId==-1)
        {
            Toast.makeText(this,"Please Select a Gender",Toast.LENGTH_LONG).show();
        }
        else
        {
/*
            mAuth.createUserWithEmailAndPassword(email.toString(), pass.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registeration.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
*/
            UserTable input=new UserTable(fname.getText().toString(),lname.getText().toString(),branch.getText().toString(),email.getText().toString(),pass.getText().toString(),
                    rollno.getText().toString(),contactno.getText().toString(),autoCompleteTextView.getText().toString(),pin.getText().toString(),dateText.getText().toString(),
                    radioSexButton.getText().toString());
            if(databaseHelper.addUserData(input))
            {
                Intent intent=new Intent(Registeration.this,MainActivity.class);
                intent.putExtra("email",email.getText().toString());
                startActivity(intent);
                Toast.makeText(this,"Data Stored",Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent=new Intent(Registeration.this,MainActivity.class);
                intent.putExtra("email",email.getText().toString());
                startActivity(intent);
                Toast.makeText(this,"Data Not Stored",Toast.LENGTH_LONG).show();
            }

        }
        }
    public void gotologin(View view)
    {
        Intent intent=new Intent(Registeration.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "-" + (month+1) + "-" + dayOfMonth;
        dateText.setText(date);
    }
}
