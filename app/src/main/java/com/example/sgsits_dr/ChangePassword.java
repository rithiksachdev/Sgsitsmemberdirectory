package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import database.DatabaseHelper;

public class ChangePassword extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private DatabaseHelper databaseHelper;
    private EditText prevpass;
    private EditText newpass;
    private EditText confirmpass;
    SharePreferenceData sharePreferenceData;
    private String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharePreferenceData sharePreferenceData=new SharePreferenceData();
        String mode= sharePreferenceData.getSharedPrefString(this,"ngtmode","no");
        if(mode.equalsIgnoreCase("yes"))
        {
            setTheme(R.style.darkTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            setTheme(R.style.AppTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        lottieAnimationView=findViewById(R.id.newpasswordanime);
        startCheckAnimation();
        databaseHelper=DatabaseHelper.getInstance(this);
        prevpass=findViewById(R.id.prevpass);
        newpass=findViewById(R.id.newpass);
        confirmpass=findViewById(R.id.confirmpass);
        sharePreferenceData=new SharePreferenceData();
        mail=sharePreferenceData.getSharedPrefString(this,"regmail","");
        findViewById(R.id.savechng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor = databaseHelper.getDataFromUser();
                    cursor.moveToFirst();
                    do {
                        String pass = cursor.getString(4);
                        String tmail = cursor.getString(3);
                        String a = prevpass.getText().toString();
                        String b = mail;
                        if (prevpass.getText().toString().equals(pass) && mail.equals(tmail)) {
                            if (newpass.getText().toString().equals(confirmpass.getText().toString())) {
                                databaseHelper.changepass(mail, newpass.getText().toString());
                            }
                        }
                    } while (cursor.moveToNext());
                }
                catch (Exception e) {
                        Toast.makeText(ChangePassword.this,"Wrong Details try Again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startCheckAnimation()
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

}
