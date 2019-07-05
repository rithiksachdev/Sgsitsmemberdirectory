package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import database.DatabaseHelper;
import database.NotificationTable;

public class NotificationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText title;
    private EditText domain;
    private EditText summary;
    DatabaseHelper databaseHelper;
    SharePreferenceData sharePreferenceData;
    TextView dateText;
    TextView dateText1;
    private EditText desc;
    private String mail;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        dateText = findViewById(R.id.stdate);
        findViewById(R.id.calendericdt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirst = true;
                showDatePickerDialog();
            }
        });
        sharePreferenceData=new SharePreferenceData();
        dateText1 = findViewById(R.id.enddate);
        findViewById(R.id.calenderic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirst = false;
                showDatePickerDialog();
            }
        });
        title=findViewById(R.id.title);
        domain=findViewById(R.id.domain);
        summary=findViewById(R.id.summary);
        desc=findViewById(R.id.desc);
        databaseHelper=DatabaseHelper.getInstance(this);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        mail=sharePreferenceData.getSharedPrefString(this,"regmail","");
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().contentEquals("")) {
                    title.setError("Please enter Title");
                    title.requestFocus();
                }
                else if(domain.getText().toString().contentEquals(""))
                {
                    domain.setError("Please enter domain");
                    domain.requestFocus();
                }
                else if(summary.getText().toString().contentEquals(""))
                {
                    summary.setError("Please enter Summary");
                    summary.requestFocus();
                }
                else if(dateText.getText().toString().contentEquals(""))
                {
                    dateText.setError("Please enter start date");
                }
                else if(dateText1.getText().toString().contentEquals(""))
                {
                    dateText1.setError("Please enter end date");
                }
                else if(desc.getText().toString().contentEquals(""))
                {
                    desc.setError("Please enter description");
                }
                else
                {
                    String ab=desc.getText().toString();
                    NotificationTable input=new NotificationTable(title.getText().toString(),domain.getText().toString(),summary.getText().toString(),dateText.getText().toString(),dateText1.getText().toString(),desc.getText().toString(),mail);
                    if(databaseHelper.addNotificationData(input))
                    {
                        Intent intent=new Intent(NotificationActivity.this,HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(NotificationActivity.this,"Data Stored",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(NotificationActivity.this,"Data Not Stored",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = (month + 1) + "-" + dayOfMonth + "-" + year;
        if (isFirst) {
            dateText.setText(date);
        } else {
            dateText1.setText(date);
        }

    }
}
