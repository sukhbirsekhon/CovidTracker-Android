package com.example.localevents;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
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

    Context con = null;

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

        new NewsAsync().execute();
    }

    class NewsAsync extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] objects)
        {
            NewDataService newsService = new NewDataService();
            try
            {
                titles = newsService.getTitle();
                descriptions = newsService.getDescription();
                contents = newsService.getContent();
                urls = newsService.getUrl();
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
                @Override
                public void run()
                {
                    try
                    {
                        for(int j = 0; j < titles.size(); j++)
                        {
                            NewsDataProvider newsDataProvider = new NewsDataProvider(titles.get(j), descriptions.get(j),
                                    contents.get(j), "More info: " + urls.get(j));

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
