package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Calendar;
import java.util.Date;

import database.DatabaseHelper;

public class ForgotPassword extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private TextView dateText;
    private EditText mail;
    LottieAnimationView lottieAnimationView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        dateText = findViewById(R.id.dob);
        findViewById(R.id.calendericon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        mail=findViewById(R.id.email);
        lottieAnimationView=findViewById(R.id.forgotlotti);
        startAnimation();
        databaseHelper=DatabaseHelper.getInstance(this);
        findViewById(R.id.submitfpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor cursor=databaseHelper.getselectDataFromUser(mail.getText().toString());
                    cursor.moveToFirst();
                    do{
                        String a=cursor.getString(cursor.getColumnIndex("email"));
                        String b=cursor.getString(cursor.getColumnIndex("password"));
                        String c=cursor.getString(cursor.getColumnIndex("dateofbirth"));
                        if(mail.getText().toString().equals(a) && dateText.getText().toString().equalsIgnoreCase(c))
                        {
                            Toast.makeText(ForgotPassword.this,"Your Password is "+b ,Toast.LENGTH_LONG).show();
                        }
                    }while (cursor.moveToNext());
                }catch (Exception e)
                {
                    Toast.makeText(ForgotPassword.this,"Your Details are Wrong " ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startAnimation()
    {
        final ValueAnimator animator=ValueAnimator.ofFloat(0f,1f).setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress((Float)animation.getAnimatedValue());
            }
        });
        if(lottieAnimationView.getProgress()==0f)
        {
            animator.start();
        }
        else
        {
            lottieAnimationView.setProgress(0f);
        }

    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,  this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year+"-"+(month+1)+"-"+dayOfMonth;
        dateText.setText(date);
    }
}
