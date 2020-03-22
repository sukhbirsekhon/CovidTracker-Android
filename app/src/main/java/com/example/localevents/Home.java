package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Home extends AppCompatActivity
{
    Button logout, callAPI;
    private TextView result;

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
                    ArrayList<JSONArray> countryNames = new ArrayList<>();
                    countryNames.add(ds.getCountryName());

                    ArrayList<JSONArray> cases = new ArrayList<>();
                    cases.add(ds.getNumberOfCases());

                    ArrayList<JSONArray> deaths = new ArrayList<>();
                    deaths.add(ds.getNumberOfDeaths());

                    ArrayList<JSONArray> totalRecovered = new ArrayList<>();
                    totalRecovered.add(ds.getTotalRecovered());

                    ArrayList<JSONArray> newDeaths = new ArrayList<>();
                    newDeaths.add(ds.getNewDeaths());

                    ArrayList<JSONArray> newCases = new ArrayList<>();
                    newCases.add(ds.getNewCases());

                    ArrayList<JSONArray> seriousCritical = new ArrayList<>();
                    seriousCritical.add(ds.getSeriousCritical());

                    ArrayList<JSONArray> activeCases = new ArrayList<>();
                    activeCases.add(ds.getActiveCases());

                    ArrayList<JSONArray> totalCasesPerMillionPopulation = new ArrayList<>();
                    totalCasesPerMillionPopulation.add(ds.getTotalCasesPerMillionPopulation());

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
    }
}
