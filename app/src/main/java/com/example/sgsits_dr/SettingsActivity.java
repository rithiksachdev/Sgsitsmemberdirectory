package com.example.sgsits_dr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    private Switch aSwitch;
    SharePreferenceData sharePreferenceData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.darkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        findViewById(R.id.chngpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this,ChangePassword.class));
            }
        });
        aSwitch=findViewById(R.id.ngtmode);
        sharePreferenceData=new SharePreferenceData();
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    sharePreferenceData.setSharedPrefString(SettingsActivity.this,"ngtmode","yes");
                    restartapp();
                }
                else
                {
                    sharePreferenceData.setSharedPrefString(SettingsActivity.this,"ngtmode","no");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartapp();
                }
            }
        });

    }
    public void restartapp()
    {
        startActivity(new Intent(this,SettingsActivity.class));
        finish();
    }
}