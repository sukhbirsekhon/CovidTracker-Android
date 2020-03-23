package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
{
    Button logout, callAPI, btnCasesByCountry;

    ArrayList<String> countryNames = new ArrayList<>();
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

        callAPI = findViewById(R.id.btnAPI);
        btnCasesByCountry = findViewById(R.id.btnCasesByCountry);
        logout = findViewById(R.id.btnLogoutH);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

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

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCasesByCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CasesByCountry.class));
            }
        });
    }
}
