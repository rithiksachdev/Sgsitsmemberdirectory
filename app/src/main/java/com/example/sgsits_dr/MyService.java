package com.example.sgsits_dr;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate()
    {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags,int startId)
    {
        Toast.makeText(MyService.this,"Hello World",Toast.LENGTH_LONG);
        return super.onStartCommand(intent,flags,startId);
    }
}