package com.example.sgsits_dr;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import database.DatabaseHelper;
import database.WorkExperienceTable;
import ipaddressutil.Utils;

public class CompanyDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText cmpname;
    private TextView joindate;
    DatabaseHelper databaseHelper;
    private EditText position;
    private Button savedetail;
    SharePreferenceData sharePreferenceData;
    private TextView enddate;
    private TextView loc;
    private TextView ipad;
    private TextView ltedit;
    LocationManager locationManager;
    LocationListener locationListener;
    String mail;
    boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        cmpname = findViewById(R.id.cmpame);
        joindate = findViewById(R.id.joindate);
        position = findViewById(R.id.position);
        enddate = findViewById(R.id.enddate);
        ltedit=findViewById(R.id.lastedit);
        ipad=findViewById(R.id.ipadd);
        savedetail = findViewById(R.id.savecmp);
        loc = findViewById(R.id.location);
        sharePreferenceData = new SharePreferenceData();
        databaseHelper = DatabaseHelper.getInstance(this);
        findViewById(R.id.calenderstCmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                showDatePickerDialog();
            }
        });
        findViewById(R.id.calenderendCmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                showDatePickerDialog();
            }
        });
        mail = sharePreferenceData.getSharedPrefString(this, "regmail", "");
        try {
            Cursor cursor = databaseHelper.getselectCMPDataFromUser(mail);
            cursor.moveToFirst();
            do {
                cmpname.setText(cursor.getString(0));
                joindate.setText(cursor.getString(1));
                position.setText(cursor.getString(2));
                enddate.setText(cursor.getString(3));
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils utils=new Utils();
        String ip=utils.getIPAddress(true);
        ipad.setText(ip);
        Date c = Calendar.getInstance().getTime();
        ltedit.setText(c.toString());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                loc.append(location.getLatitude() + " " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            return;
        }
        else{
            configurebutton();
        }
    }

    private void configurebutton() throws SecurityException{
        savedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws SecurityException {

                if (cmpname.getText().toString().contentEquals("")) {
                    cmpname.setError("Please enter your Name");
                    cmpname.requestFocus();
                } else if (joindate.getText().toString().contentEquals("")) {
                    joindate.setError("Please enter your Name");
                } else if (position.getText().toString().contentEquals("")) {
                    position.setError("Please enter your Name");
                    position.requestFocus();
                } else if (enddate.getText().toString().contentEquals("")) {
                    enddate.setError("Please enter your Name");
                    enddate.requestFocus();
                } else {
                    WorkExperienceTable input = new WorkExperienceTable(cmpname.getText().toString(), joindate.getText().toString(), position.getText().toString(), enddate.getText().toString(), mail);
                    if (databaseHelper.addWorkData(input)) {
                        //  databaseHelper.updateselectCMPDataFromUser(cmpname.getText().toString(),joindate.getText().toString(),position.getText().toString(),enddate.getText().toString(),mail);
                        try {
                            Cursor cursor = databaseHelper.getselectCMPDataFromUser(mail);
                            cursor.moveToFirst();
                            do {
                                cmpname.setText(cursor.getString(0));
                                joindate.setText(cursor.getString(1));
                                position.setText(cursor.getString(2));
                                enddate.setText(cursor.getString(3));
                            } while (cursor.moveToNext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(CompanyDetail.this, "Data Stored", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CompanyDetail.this, "Data Not Stored", Toast.LENGTH_LONG).show();
                    }
                }
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                criteria.setPowerRequirement(Criteria.POWER_LOW);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setSpeedRequired(false);
                criteria.setCostAllowed(true);
                criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
                criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
                locationManager.requestSingleUpdate(criteria, locationListener, null);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurebutton();
                }
        }
    }

    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date=year+"-"+(month+1)+"-"+dayOfMonth;
        if(flag)
        {
            joindate.setText(date);
        }
        if(!flag)
        {
            enddate.setText(date);
        }

    }
}
