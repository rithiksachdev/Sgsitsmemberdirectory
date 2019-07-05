package com.example.sgsits_dr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.example.sgsits_dr.models.NotificationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import adp.RecyclerViewAdp;
import database.DatabaseHelper;

import static android.widget.LinearLayout.VERTICAL;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    String tmail;
    ArrayList<NotificationModel> strings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper=DatabaseHelper.getInstance(this);
        /*Set up Linear layout for VERTICAL or HORIZONTAL Scrolling in Recycler view*/
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        /*Set up Linear layout for VERTICAL or HORIZONTAL divider*/
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this,
                        VERTICAL));

        try{
            Cursor cursor=databaseHelper.getNotificationFromUser();
            cursor.moveToFirst();
            do {
                String title = cursor.getString(0);
                String domain = cursor.getString(1);
                String summary = cursor.getString(2);
                String startdate = cursor.getString(3);
                String enddate = cursor.getString(4);
                String desc = cursor.getString(5);
                strings.add(new NotificationModel(title, desc, summary, domain, startdate, enddate));
            } while (cursor.moveToNext());
        }catch(Exception e)
        {

           e.printStackTrace();
        }
        strings.add(new NotificationModel("Jobs","Job in a Dream Company as a web developer, The students are expected to submit the forms before the last date and bring all the documents with them, 3 passport size photographs and 2 copies of their resume", "Job for CS/IT students as a front end web developer", "JOB", "2019-11-03", "2019-11-12"));
        strings.add(new NotificationModel("Holidays","Wishing all the students a very Happy diwali, Lord ram won his battle against evil hope you win against your fears", "Holiday for 1 Week", "Holiday", "2018-09-03", "2018-09-10"));
        strings.add(new NotificationModel("Expert Lecture","Dr. Chande sir will addressing all students for this fridays' expert lecture and enlighten students about android training ", "Dr. Chande Sir's Expert Lecture", "Expert Talk", "2019-06-28", "2019-06-28"));
        strings.add(new NotificationModel("Jobs","Job in a Dream Company as a web developer, The students are expected to submit the forms before the last date and bring all the documents with them, 3 passport size photographs and 2 copies of their resume", "Job for CS/IT students as a front end web developer", "JOB", "2019-11-03", "2019-11-12"));
        strings.add(new NotificationModel("Holidays","Wishing all the students a very Happy diwali, Lord ram won his battle against evil hope you win against your fears", "Holiday for 1 Week", "Holiday", "2018-09-03", "2018-09-10"));
        strings.add(new NotificationModel("Expert Lecture","Dr. Chande sir will addressing all students for this fridays' expert lecture and enlighten students about android training ", "Dr. Chande Sir's Expert Lecture", "Expert Talk", "2019-06-28", "2019-06-28"));
        RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(this, strings);
        recyclerView.setAdapter(recyclerViewAdp);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
//
//    public void DividerItemDecoration{
//        new DividerItemDecoration(this, LinearLayout.VERTICAL);
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_editprofile) {
            Intent intent=new Intent(this, Editprofile.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            startActivity(new Intent(HomeActivity.this,SearchActivity.class));

        } else if (id == R.id.notif) {
            startActivity(new Intent(HomeActivity.this,NotificationActivity.class));
        }  else if (id == R.id.setting) {
            startActivity(new Intent(HomeActivity.this,SettingsActivity.class));

        } else if (id == R.id.about) {
            startActivity(new Intent(HomeActivity.this,AboutUs.class));
        }
        else if (id == R.id.logout) {
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}