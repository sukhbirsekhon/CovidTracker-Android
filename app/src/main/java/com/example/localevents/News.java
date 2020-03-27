package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class News extends AppCompatActivity
{
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Intent c = new Intent(News.this, Home.class);
                    startActivity(c);
                    break;
                case R.id.nav_cases:
                    Intent a = new Intent(News.this, CasesByCountry.class);
                    startActivity(a);
                    break;
                case R.id.nav_world:
                    Intent b = new Intent(News.this, MapsActivity.class);
                    startActivity(b);
                    break;
                case R.id.nav_news:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
}
