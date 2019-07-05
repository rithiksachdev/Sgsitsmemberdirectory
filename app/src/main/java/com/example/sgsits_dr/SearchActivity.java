package com.example.sgsits_dr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Switch;

import com.example.sgsits_dr.models.NotificationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adp.RecyclerViewAdp;
import database.DatabaseHelper;
import database.NotificationTable;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private FloatingActionButton fb;
    private String s;
    ArrayList<NotificationModel> notificationTable=new ArrayList<>();
    DatabaseHelper databaseHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView=findViewById(R.id.search_bar);
        recyclerView=findViewById(R.id.searchrec);
        fb=findViewById(R.id.floatingActionButton);
        databaseHelper=DatabaseHelper.getInstance(this);
        cursor=databaseHelper.getNotificationFromUser();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(cursor!=null)
                {
                    if(cursor.getCount()>0)
                    {
                        cursor.moveToFirst();
                        do{
                            String title=cursor.getString(0);
                            String domain=cursor.getString(1);
                            String summary=cursor.getString(2);
                            String startdate=cursor.getString(3);
                            String enddate=cursor.getString(4);
                            String desc=cursor.getString(5);
                            if(title.toLowerCase().contains(query.toLowerCase()))
                            {
                                notificationTable.add(
                                    new NotificationModel(title,desc,summary,domain,startdate,enddate
                                    ));
                            }
                            else if(domain.toLowerCase().contains(query.toLowerCase()))
                            {
                                notificationTable.add(
                                        new NotificationModel(title,desc,summary,domain,startdate,enddate
                                        ));
                            }
                            else if(summary.toLowerCase().contains(query.toLowerCase()))
                            {
                                notificationTable.add(
                                        new NotificationModel(title,desc,summary,domain,startdate,enddate
                                        ));
                            }
                            else if(startdate.toLowerCase().contains(query.toLowerCase()))
                            {
                                notificationTable.add(
                                        new NotificationModel(title,desc,summary,domain,startdate,enddate
                                        ));
                            }
                            else if(enddate.contains(query))
                            {
                                notificationTable.add(
                                        new NotificationModel(title,desc,summary,domain,startdate,enddate
                                        ));
                            }
                            else if(desc.toLowerCase().contains(query.toLowerCase()))
                            {
                                notificationTable.add(
                                        new NotificationModel(title,desc,summary,domain,startdate,enddate
                                        ));
                            }
                            RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(SearchActivity.this, notificationTable);
                            recyclerView.setAdapter(recyclerViewAdp);
                        }while (cursor.moveToNext());
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}
