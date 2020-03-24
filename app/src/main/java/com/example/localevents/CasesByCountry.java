package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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

    ArrayList<String> countryNames = new ArrayList<>();
    List<String> cases = new ArrayList<>();
    List<String> deaths = new ArrayList<>();
    List<String> totalRecovered = new ArrayList<>();
    List<String> activeCases = new ArrayList<>();
    List<String> newCases = new ArrayList<>();
    List<String> newDeaths = new ArrayList<>();
    List<String> seriousCritical = new ArrayList<>();
    List<String> totalCasesPerMillionPopulation = new ArrayList<>();

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
            }
            return false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cases_by_country);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        DataService ds = new DataService();

        try
        {
            countryNames = ds.getCountryName();
            cases = ds.getNumberOfCases();
            deaths = ds.getNumberOfDeaths();
            totalRecovered = ds.getTotalRecovered();
            newDeaths = ds.getNewDeaths();
            newCases = ds.getNewCases();
            seriousCritical = ds.getSeriousCritical();
            activeCases = ds.getActiveCases();
            totalCasesPerMillionPopulation = ds.getTotalCasesPerMillionPopulation();

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
        try
        {
            for(int j = 0; j < countryNames.size(); j ++)
            {
                ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider(countryNames.get(j),
                        cases.get(j), activeCases.get(j),
                        totalRecovered.get(j),
                        deaths.get(j), newCases.get(j),
                        newDeaths.get(j),
                        seriousCritical.get(j));

                listItems.add(listCasesDataProvider);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ListCasesAdapter(listItems, this );
        recyclerView.setAdapter(adapter);
    }
}
