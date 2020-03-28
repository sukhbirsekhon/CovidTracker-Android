package com.example.localevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
    private List<ListCasesDataProvider> listItems2;
    private ProgressBar progressBar;
    EditText txtSearch;
    ImageButton btnRefresh, btnSearch, btnClear;

    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> resultSet = new ArrayList<>();
    List<String> cases = new ArrayList<>();
    List<String> deaths = new ArrayList<>();
    List<String> totalRecovered = new ArrayList<>();
    List<String> activeCases = new ArrayList<>();
    List<String> newCases = new ArrayList<>();
    List<String> newDeaths = new ArrayList<>();
    List<String> seriousCritical = new ArrayList<>();
    List<String> totalCasesPerMillionPopulation = new ArrayList<>();

    ArrayList<String> cn = new ArrayList<>();
    List<String> cs = new ArrayList<>();
    List<String> ac = new ArrayList<>();
    List<String> tr = new ArrayList<>();
    List<String> dt = new ArrayList<>();

    EditText searchedText;
    int searchedCountryPosition;
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
                case R.id.nav_news:
                    Intent c = new Intent(CasesByCountry.this, News.class);
                    startActivity(c);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cases_by_country);

        progressBar = findViewById(R.id.progressBar6);
        btnRefresh = findViewById(R.id.btnRefresh2);
        btnSearch = findViewById(R.id.btnSearch);
        searchedText = (EditText) findViewById(R.id.txtSearch);
        btnClear = findViewById(R.id.btnClear);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        listItems2 = new ArrayList<>();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        con = this;

        progressBar.setVisibility(View.VISIBLE);
        new CasesByCountryAsync().execute();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedText.setBackgroundResource(0);
                progressBar.setVisibility(View.VISIBLE);
                new CasesByCountryAsync2().execute();
            }
        });
        searchedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedText.setBackgroundResource(R.drawable.rectangle_box);
                searchedText.setTextColor(Color.rgb(255, 26, 26));
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedText.getText().clear();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                searchedText.setBackgroundResource(0);
                try
                {
                    progressBar.setVisibility(View.VISIBLE);
                    resultSet = filter(searchedText.getText().toString());
                    String searchedCountry = resultSet.get(0);

                    for (int i = 0; i < countryNames.size(); i++) {
                        if(searchedCountry == null)
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a country", Toast.LENGTH_LONG).show();
                        }
                        else if (countryNames.get(i).matches(searchedCountry)) {
                            searchedCountryPosition = i;
                        }
                    }
                    DataService ds = new DataService();
                    cn = ds.getCountryName();
                    cs = ds.getNumberOfCases();
                    ac = ds.getActiveCases();
                    tr = ds.getTotalRecovered();
                    dt = ds.getNumberOfDeaths();
                }
                catch (IOException | IndexOutOfBoundsException e)
                {
                    Toast.makeText(getApplicationContext(), "'" + searchedText.getText().toString() + "' is not a country.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (JSONException e)
                {
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

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run() {
                        try
                        {
                            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider("#" + (searchedCountryPosition+1) + " "+ cn.get(searchedCountryPosition),
                                    cs.get(searchedCountryPosition), ac.get(searchedCountryPosition),
                                    tr.get(searchedCountryPosition),
                                    dt.get(searchedCountryPosition));
                            System.out.println(cn.get(searchedCountryPosition));
                            listItems2.removeAll(listItems2);
                            listItems2.add(listCasesDataProvider);
                            System.out.println(listItems2.toString());
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                        adapter = new ListCasesAdapter(listItems2, con);
                        recyclerView.setAdapter(adapter);
                    }
                });
                progressBar.setVisibility(View.GONE);
            }

        });

    }

    private ArrayList<String> filter(String searchText) throws IOException, JSONException {
        ArrayList<String> filteredList = new ArrayList<>();
        DataService ds = new DataService();
        countryNames = ds.getCountryName();
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

        for(String s : countryNames)
        {
            if(s.toLowerCase().matches(searchText.toLowerCase()))
            {
                filteredList.add(s);
            }
        }

        return filteredList;
    }

    class searchAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.setVisibility(View.GONE);
        }
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
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        for(int j = 0; j < countryNames.size(); j ++)
                        {
                            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider("#" + (j+1) + " "+ countryNames.get(j),
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
                            ListCasesDataProvider listCasesDataProvider = new ListCasesDataProvider("#" + (j+1) + " " + countryNames.get(j),
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

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        searchedText.setBackgroundResource(0);
    }
}
