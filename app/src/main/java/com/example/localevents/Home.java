package com.example.localevents;


import android.content.Intent;

import android.graphics.Color;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView txtConfirmedCases, txtRecoveredCases, txtDeathCases, txtGlobalNewCases, txtGlobalNewDeaths, txtMortality;
    ImageButton btnRefresh;
    private ProgressBar progressBar;

    ArrayList<String> globalCases = new ArrayList<>();
    ArrayList<String> globalRecovered = new ArrayList<>();
    ArrayList<String> globalFatal = new ArrayList<>();
    ArrayList<String> globalNewCases = new ArrayList<>();
    ArrayList<String> globalNewDeaths = new ArrayList<>();
    List<String> confirmedCases = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    break;
                case R.id.nav_cases:
                    Intent a = new Intent(Home.this, CasesByCountry.class);
                    startActivity(a);
                    break;
                case R.id.nav_world:
                    Intent b = new Intent(Home.this, MapsActivity.class);
                    startActivity(b);
                    break;
                case R.id.nav_news:
                    Intent c = new Intent(Home.this, News.class);
                    startActivity(c);
                    break;
                case R.id.nav_help:
                    Intent d = new Intent(Home.this, Help.class);
                    startActivity(d);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtConfirmedCases = findViewById(R.id.txtConfirmedCases);
        txtRecoveredCases = findViewById(R.id.txtRecoveredCases);
        txtDeathCases = findViewById(R.id.txtDeathCases);
        txtGlobalNewCases = findViewById(R.id.txtNewCases);
        txtGlobalNewDeaths = findViewById(R.id.txtNewDeaths);
        btnRefresh = findViewById(R.id.btnRefresh);
        progressBar = findViewById(R.id.progressBar);
        txtMortality = findViewById(R.id.txtMortality);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        DataService ds = new DataService();
        try {
            confirmedCases = ds.getNumberOfCases();
            countryNames = ds.getCountryName();
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
                    wait(100);
                    i++;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        ArrayList<BarEntry> entries = new ArrayList<>();

        BarChart barChart = (BarChart) findViewById(R.id.bargraph);

        for(int j = 0; j < 5; j++)
        {
            try
            {
                int c = Integer.parseInt(confirmedCases.get(j).replaceAll(",", ""));
                entries.add(new BarEntry(c, j));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        BarDataSet bardataset = new BarDataSet(entries, "Confirmed cases (Top 5 countries)");

        ArrayList<String> labels = new ArrayList<String>();
        for(int k = 0; k < 5; k++)
        {
            try
            {
                labels.add(countryNames.get(k));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        BarData data = new BarData(labels, bardataset);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        YAxis right = barChart.getAxisRight();
        right.setDrawLabels(false); // no axis labels
        right.setDrawAxisLine(false); // no axis line
        right.setDrawGridLines(false); // no grid lines
        right.setDrawZeroLine(true);

        YAxis left = barChart.getAxisLeft();
        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true);

        barChart.setData(data);
        barChart.setDrawGridBackground(false);
        barChart.setDescription("");  // set the description
        barChart.animateXY(5000,5000);
        barChart.setScaleEnabled(true);
        barChart.setDrawValueAboveBar(true);
        barChart.setDrawGridBackground(false);
        barChart.setDoubleTapToZoomEnabled(true);
        barChart.setPinchZoom(true);

        bardataset.setBarSpacePercent(40f);
        bardataset.setColor(R.color.colorAccent);

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
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            int i =0;
            synchronized (this)
            {
                while (i<10)
                {
                    try {
                        wait(100);
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
                        double cases = Double.parseDouble(globalCases.get(0).replaceAll(",", ""));
                        double deaths = Double.parseDouble(globalFatal.get(0).replaceAll(",", ""));
                        double mortality = (deaths / cases) * 100;
                        double mm = Math.round(mortality * 100.0) / 100.0;
                        String m = String.valueOf(mm);
                        txtMortality.setText(m + "%");
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
                        wait(100);
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
                        double cases = Double.parseDouble(globalCases.get(0).replaceAll(",", ""));
                        double deaths = Double.parseDouble(globalFatal.get(0).replaceAll(",", ""));
                        double mortality = (deaths / cases) * 100;
                        double mm = Math.round(mortality * 100.0) / 100.0;
                        String m = String.valueOf(mm);
                        txtMortality.setText(m + "%");
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
