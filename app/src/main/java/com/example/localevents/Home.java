package com.example.localevents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Home extends AppCompatActivity
{
    TextView txtConfirmedCases, txtRecoveredCases, txtDeathCases, txtGlobalNewCases, txtGlobalNewDeaths;
    ImageButton btnRefresh;
    private ProgressBar progressBar;

    ArrayList<String> globalCases = new ArrayList<>();
    ArrayList<String> globalRecovered = new ArrayList<>();
    ArrayList<String> globalFatal = new ArrayList<>();
    ArrayList<String> globalNewCases = new ArrayList<>();
    ArrayList<String> globalNewDeaths = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    break;
                case R.id.nav_cases:
                    Intent a = new Intent(Home.this, CasesByCountry.class);
                    startActivity(a);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtConfirmedCases = findViewById(R.id.txtConfirmedCases);
        txtRecoveredCases = findViewById(R.id.txtRecoveredCases);
        txtDeathCases = findViewById(R.id.txtDeathCases);
        txtGlobalNewCases = findViewById(R.id.txtNewCases);
        txtGlobalNewDeaths = findViewById(R.id.txtNewDeaths);
        btnRefresh = findViewById(R.id.btnRefresh);
        progressBar = findViewById(R.id.progressBar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        progressBar.setVisibility(View.VISIBLE);
        new HomeAsync2().execute();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new HomeAsync().execute();
            }
        });

        txtConfirmedCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CasesByCountry.class));
            }
        });
    }

    class HomeAsync extends AsyncTask
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Object[] objects)
        {
            GlobalDataService gs = new GlobalDataService();
            try
            {
                globalCases = gs.getGlobalCases();
                globalRecovered = gs.getGlobalRecovered();
                globalFatal = gs.getGlobalFatal();
                globalNewCases = gs.getGlobalNewCases();
                globalNewDeaths = gs.getGlobalNewDeaths();
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
                        wait(300);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run()
                {
                    try
                    {
                        txtConfirmedCases.setText(globalCases.get(0));
                        txtRecoveredCases.setText(globalRecovered.get(0));
                        txtDeathCases.setText((globalFatal.get(0)));
                        txtGlobalNewCases.setText((globalNewCases.get(0)));
                        txtGlobalNewDeaths.setText((globalNewDeaths.get(0)));
                    } catch (IndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }

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
    class HomeAsync2 extends AsyncTask
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Object[] objects)
        {
            GlobalDataService gs = new GlobalDataService();
            try
            {
                globalCases = gs.getGlobalCases();
                globalRecovered = gs.getGlobalRecovered();
                globalFatal = gs.getGlobalFatal();
                globalNewCases = gs.getGlobalNewCases();
                globalNewDeaths = gs.getGlobalNewDeaths();
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
                        wait(300);
                        i++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run()
                {
                    try
                    {
                        txtConfirmedCases.setText(globalCases.get(0));
                        txtRecoveredCases.setText(globalRecovered.get(0));
                        txtDeathCases.setText((globalFatal.get(0)));
                        txtGlobalNewCases.setText((globalNewCases.get(0)));
                        txtGlobalNewDeaths.setText((globalNewDeaths.get(0)));
                    } catch (IndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }
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
