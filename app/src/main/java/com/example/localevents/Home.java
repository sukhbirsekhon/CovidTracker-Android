package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
{
    Button logout, callAPI;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListCasesDataProvider> listItems;
    List<String> countryNames = new ArrayList<>();
    List<String> cases = new ArrayList<>();
    List<String> deaths = new ArrayList<>();
    List<String> totalRecovered = new ArrayList<>();
    List<String> newDeaths = new ArrayList<>();
    List<String> newCases = new ArrayList<>();
    List<String> seriousCritical = new ArrayList<>();
    List<String> activeCases = new ArrayList<>();
    List<String> totalCasesPerMillionPopulation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.btnLogoutH);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        callAPI = findViewById(R.id.btnAPI);
        callAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int i = 0;
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

                    synchronized (this)
                    {
                        while (i<10)
                        {
                            try {
                                wait(150);
                                i++;
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                    callAPI.setText("Successfully created objects");
                    System.out.println(countryNames);
                    System.out.println(cases);
                    System.out.println(deaths);
                    System.out.println(totalRecovered);
                    System.out.println(newDeaths);
                    System.out.println(newCases);
                    System.out.println(seriousCritical);
                    System.out.println(activeCases);
                    System.out.println(totalCasesPerMillionPopulation);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

//        for(String s: countryNames)
//        {
//            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider("heading " + (i+1),
//                    "fjkasdjflasd");
//
//            listItems.add(listCasesDataProvider);
//        }
//
//        adapter = new ListCasesAdapter(listItems, this);
//
//        recyclerView.setAdapter(adapter);

    }
}
