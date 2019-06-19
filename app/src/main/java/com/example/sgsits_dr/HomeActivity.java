package com.example.sgsits_dr;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

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

import java.util.ArrayList;

import adp.RecyclerViewAdp;

import static android.widget.LinearLayout.*;
import static android.widget.LinearLayout.VERTICAL;

public class HomeActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        /*Set up Linear layout for VERTICAL or HORIZONTAL Scrolling in Recycler view*/
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        /*Set up Linear layout for VERTICAL or HORIZONTAL divider*/
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this,
                        VERTICAL));

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Student 1");
        strings.add("Student 2");
        strings.add("Student 3");
        strings.add("Student 4");
        strings.add("Student 5");
        strings.add("Student 6");
        strings.add("Student 7");
        strings.add("Student 8");
        RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(this, strings);
        recyclerView.setAdapter(recyclerViewAdp);
        Intent intent=new Intent(this,emptyactivity.class);
        startActivity(intent);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

   // public void DividerItemDecoration{
     //   new DividerItemDecoration(this, LinearLayout.VERTICAL);
    //}

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dir) {
            // Handle the camera action
        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_comm) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_editprofile) {
            Intent intent=new Intent(this,edit_profile.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
