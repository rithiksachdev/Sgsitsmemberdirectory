package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import database.DatabaseHelper;
import database.EducationDetailsTable;

public class EditEducationDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText insname;
    private AutoCompleteTextView board;
    private EditText result;
    private TextView startdate;
    private TextView enddate;
    private String mail;
    private EditText degree;
    DatabaseHelper databaseHelper;
    SharePreferenceData sharePreferenceData;
    boolean flag=false,flag1=false,dateflag=false;
    private String[] boards=new String[]{
            "CBSE","MP","ICSE"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_education_details);
        insname=findViewById(R.id.institutionName);
        board=findViewById(R.id.board);
        result=findViewById(R.id.result);
        startdate=findViewById(R.id.startdateedu);
        enddate=findViewById(R.id.enddateedu);
        degree=findViewById(R.id.degree);
        sharePreferenceData=new SharePreferenceData();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,boards);
        board.setAdapter(arrayAdapter);
        databaseHelper=DatabaseHelper.getInstance(this);
        findViewById(R.id.calenderst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateflag=false;
                showDatePickerDialog();
            }
        });
        findViewById(R.id.calenderend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateflag=true;
                showDatePickerDialog();
            }
        });
        mail=sharePreferenceData.getSharedPrefString(this,"regmail","");
        try {
            Cursor cursor1 = databaseHelper.getselectEDUDataFromUser(mail);
            cursor1.moveToFirst();
                do {
                    String txt = cursor1.getString(0);
                    insname.setText(cursor1.getString(0));
                    board.setText(cursor1.getString(1));
                    result.setText(cursor1.getString(2));
                    startdate.setText(cursor1.getString(3));
                    enddate.setText(cursor1.getString(4));
                    degree.setText(cursor1.getString(5));
                } while (cursor1.moveToNext());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        findViewById(R.id.nxtimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insname.getText().toString().contentEquals("")) {
                    insname.setError("Please enter Institute Name");
                    insname.requestFocus();
                }
                else if(board.getText().toString().contentEquals(""))
                {
                    board.setError("Please enter Board Name");
                    board.requestFocus();
                }
                else if(result.getText().toString().contentEquals(""))
                {
                    result.setError("Please enter Result");
                    result.requestFocus();
                }
                else if(startdate.getText().toString().contentEquals(""))
                {
                    startdate.setError("Please enter Start Date");
                }
                else if(enddate.getText().toString().contentEquals(""))
                {
                    enddate.setError("Please enter End Date");
                }

                else if(degree.getText().toString().contentEquals(""))
                {
                    degree.setError("Please enter Degree");
                    degree.requestFocus();
                }
                else
                {
                    flag=true;
                    flag1=true;
                }
                if (v.getId() == R.id.nxtimg && flag && flag1) {
                    EducationDetailsTable educationDetailsTable=new EducationDetailsTable(insname.getText().toString(),board.getText().toString(),result.getText().toString(),
                            startdate.getText().toString(),enddate.getText().toString(),degree.getText().toString(),mail);

                    if(databaseHelper.addEducationData(educationDetailsTable))
                    {

                        try {
                            databaseHelper.updateselectEduDataFromUser(insname.getText().toString(), board.getText().toString(), result.getText().toString(), startdate.getText().toString(), enddate.getText().toString(), degree.getText().toString(), mail);
                            Cursor cursor1 = databaseHelper.getselectEDUDataFromUser(mail);
                            cursor1.moveToFirst();
                                do {
                                    String txt = cursor1.getString(0);
                                    insname.setText(cursor1.getString(0));
                                    board.setText(cursor1.getString(1));
                                    result.setText(cursor1.getString(2));
                                    startdate.setText(cursor1.getString(3));
                                    enddate.setText(cursor1.getString(4));
                                    degree.setText(cursor1.getString(5));
                                } while (cursor1.moveToNext());
                           }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        Toast.makeText(EditEducationDetail.this,"Data Stored",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditEducationDetail.this, CompanyDetail.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(EditEducationDetail.this,"Data Not Stored",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date=year+"-"+(month+1)+"-"+dayOfMonth;
        if(dateflag)
        {
            enddate.setText(date);
        }
        if(!dateflag)
        {
            startdate.setText(date);
        }
    }
}
