package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLoginBtn;
    private EditText meditview1;
    private EditText meditview2;
    private String str;
    private String str1;
    private int val;
    private boolean flag = false;
    private boolean flag1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginBtn = (Button) findViewById(R.id.login);
        mLoginBtn.setOnClickListener(this);
        meditview1 = (EditText) findViewById(R.id.userName);
        meditview2 = (EditText) findViewById(R.id.password);
      /* mLoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(MainActivity.this,"Login Success hogya",Toast.LENGTH_LONG).show();
           }
       });
       */
    }

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
            try {
                Toast.makeText(MainActivity.this, "Login Success hoga", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }
    public void performregisteration(View v)
    {
        Intent intent = new Intent(MainActivity.this, Registeration.class);
        intent.putExtra("Data:","Hello Sgsits");
        startActivity(intent);
    }

  /*  public void performLogin(View view){
        Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show();
    }
    */
}
