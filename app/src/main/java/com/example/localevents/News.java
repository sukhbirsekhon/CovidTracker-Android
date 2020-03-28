package com.example.localevents;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NewsDataProvider> listItems;

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> publishedAt = new ArrayList<>();
    ArrayList<String> urlToImage = new ArrayList<>();

    Spinner spinner;
    Context con = null;
    String spinnerValue = "US";

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        con = this;

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        spinner = findViewById(R.id.spinner);

        new NewsAsync().execute();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(News.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = spinner.getSelectedItem().toString();
                String s = spinnerValue.substring(0,2);
                NewDataService newsService = new NewDataService();
                try
                {
                    titles = newsService.getTitle(s, getApplicationContext());
                    descriptions = newsService.getDescription(s, getApplicationContext());
                    contents = newsService.getContent(s, getApplicationContext());
                    urls = newsService.getUrl(s, getApplicationContext());
                    publishedAt = newsService.getPublishedDate(s, getApplicationContext());
                    urlToImage = newsService.getImageUrl(s, getApplicationContext());
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"No news available right now! Come back later :)",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
                int i =0;
                synchronized (this)
                {
                    while (i<10)
                    {
                        try {
                            wait(50);
                            i++;
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
                runOnUiThread(new Runnable()
                {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run()
                    {
                        try
                        {
                            listItems.removeAll(listItems);
                            for(int j = 0; j < titles.size(); j++)
                            {
                                NewsDataProvider newsDataProvider = new NewsDataProvider(titles.get(j), descriptions.get(j),
                                        contents.get(j), "More info: " + urls.get(j), Instant.parse(publishedAt.get(j)).atOffset(ZoneOffset.MAX).format(DateTimeFormatter.ofPattern("hh:mm a")), urlToImage.get(j));
                                listItems.add(newsDataProvider);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        adapter = new NewsAdapter(listItems, con);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class NewsAsync extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] objects)
        {
            NewDataService newsService = new NewDataService();
            try
            {
                titles = newsService.getTitle(spinnerValue, getApplicationContext());
                descriptions = newsService.getDescription(spinnerValue, getApplicationContext());
                contents = newsService.getContent(spinnerValue, getApplicationContext());
                urls = newsService.getUrl(spinnerValue, getApplicationContext());
                publishedAt = newsService.getPublishedDate(spinnerValue, getApplicationContext());
                urlToImage = newsService.getImageUrl(spinnerValue, getApplicationContext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            int i =0;
            synchronized (this)
            {
                while (i<10)
                {
                    try {
                        wait(50);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            runOnUiThread(new Runnable()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run()
                {
                    try
                    {
                        for(int j = 0; j < titles.size(); j++)
                        {
                            NewsDataProvider newsDataProvider = new NewsDataProvider(titles.get(j), descriptions.get(j),
                                    contents.get(j), "More info: " + urls.get(j), Instant.parse(publishedAt.get(j)).atOffset(ZoneOffset.MAX).format(DateTimeFormatter.ofPattern("hh:mm a")), urlToImage.get(j));

                            listItems.add(newsDataProvider);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    adapter = new NewsAdapter(listItems, con);
                    recyclerView.setAdapter(adapter);
                }
            });
            return null;
        }
    }
}
