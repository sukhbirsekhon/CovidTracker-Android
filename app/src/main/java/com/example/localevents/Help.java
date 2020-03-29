package com.example.localevents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help extends AppCompatActivity
{
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    Intent a = new Intent(Help.this, Home.class);
                    startActivity(a);
                    break;
                case R.id.nav_cases:
                    Intent b = new Intent(Help.this, CasesByCountry.class);
                    startActivity(b);
                    break;
                case R.id.nav_world:
                    Intent d = new Intent(Help.this, MapsActivity.class);
                    startActivity(d);
                    break;
                case R.id.nav_news:
                    Intent c = new Intent(Help.this, News.class);
                    startActivity(c);
                    break;
                case R.id.nav_help:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    public void selfOnlineTeat(View view)
    {
        Intent onlineTestIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/testing.html"));
        startActivity(onlineTestIntent);

    }
}
