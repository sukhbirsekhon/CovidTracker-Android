package com.example.localevents;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CasesByCountry extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListCasesDataProvider> listItems;
    private ProgressBar progressBar;
    ImageButton btnRefresh;

    ArrayList<String> countryNames = new ArrayList<>();
    List<String> cases = new ArrayList<>();
    List<String> deaths = new ArrayList<>();
    List<String> totalRecovered = new ArrayList<>();
    List<String> activeCases = new ArrayList<>();
    List<String> newCases = new ArrayList<>();
    List<String> newDeaths = new ArrayList<>();
    List<String> seriousCritical = new ArrayList<>();
    List<String> totalCasesPerMillionPopulation = new ArrayList<>();
    Context con = null;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    Intent a = new Intent(CasesByCountry.this, Home.class);
                    startActivity(a);
                    break;
                case R.id.nav_cases:
                    break;
                case R.id.nav_world:
                    Intent b = new Intent(CasesByCountry.this, MapsActivity.class);
                    startActivity(b);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cases_by_country);

        progressBar = findViewById(R.id.progressBar6);
        btnRefresh = findViewById(R.id.btnRefresh2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        con = this;

        progressBar.setVisibility(View.VISIBLE);
        new CasesByCountryAsync().execute();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new CasesByCountryAsync2().execute();
            }
        });
    }

    class CasesByCountryAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            DataService ds = new DataService();
            try
            {
                countryNames = ds.getCountryName();
                cases = ds.getNumberOfCases();
                deaths = ds.getNumberOfDeaths();
                totalRecovered = ds.getTotalRecovered();
                activeCases = ds.getActiveCases();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int i =0;

            synchronized (this)
            {
                while (i<10)
                {
                    try {
                        wait(200);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        for(int j = 0; j < countryNames.size(); j ++)
                        {
                            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider(countryNames.get(j),
                                    cases.get(j), activeCases.get(j),
                                    totalRecovered.get(j),
                                    deaths.get(j));

                            listItems.add(listCasesDataProvider);
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                    adapter = new ListCasesAdapter(listItems, con);
                    recyclerView.setAdapter(adapter);
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.setVisibility(View.GONE);
        }
    }

    class CasesByCountryAsync2 extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            DataService ds = new DataService();
            try
            {
                countryNames = ds.getCountryName();
                cases = ds.getNumberOfCases();
                deaths = ds.getNumberOfDeaths();
                totalRecovered = ds.getTotalRecovered();
                activeCases = ds.getActiveCases();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int i =0;

            synchronized (this)
            {
                while (i<10)
                {
                    try {
                        wait(200);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        for(int j = 0; j < countryNames.size(); j ++)
                        {
                            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider(countryNames.get(j),
                                    cases.get(j), activeCases.get(j),
                                    totalRecovered.get(j),
                                    deaths.get(j));

                            listItems.add(listCasesDataProvider);
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                    adapter = new ListCasesAdapter(listItems, con);
                    recyclerView.setAdapter(adapter);

                    Toast toast = Toast.makeText(getApplicationContext(),"Updated the records.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
