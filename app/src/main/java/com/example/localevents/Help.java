package com.example.localevents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.raw.download).into(imageView);

        TextView heading = findViewById(R.id.heading);
        String text = "<font color='#000000'> #STAYHOME </font><font color='#B71C1C'>#STAYSAFE</font>";
        heading.setText(Html.fromHtml(text));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    public void selfOnlineTeat(View view)
    {
        Intent onlineTestIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/testing.html"));
        startActivity(onlineTestIntent);
    }

    public void thread(View view)
    {
        Intent a = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/disinfecting-your-home.html"));
        startActivity(a);
    }

    public void ifYouAreSick(View view)
    {
        Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/if-you-are-sick/steps-when-sick.html"));
        startActivity(b);
    }
    public void home(View view)
    {
        Intent c = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/disinfecting-your-home.html"));
        startActivity(c);
    }
    public void protext(View view)
    {
        Intent d = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention.html"));
        startActivity(d);
    }
    public void symp(View view)
    {
        Intent d = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/index.html"));
        startActivity(d);
    }
    public void cdc(View view)
    {
        Intent e = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cdc.gov/covid19"));
        startActivity(e);
    }
    public void screeningTool(View view)
    {
        Intent e = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apple.com/covid19"));
        startActivity(e);
    }
}
