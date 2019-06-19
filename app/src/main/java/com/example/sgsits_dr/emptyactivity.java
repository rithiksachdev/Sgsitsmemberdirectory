package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class emptyactivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptyactivity);
        
        showDatePickerDialog();
    }
    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this,Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.getInstance().DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date= "month/day/year : "+month+ "/" + dayOfMonth + "/" + year;
        Toast.makeText(this,date,Toast.LENGTH_LONG).show();
    }



}

