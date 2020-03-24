package com.example.localevents;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    TextView txtConfirmedCases, txtRecoveredCases, txtDeathCases, txtGlobalNewCases, txtGlobalNewDeaths, txtUpdatedTime;
    ImageButton btnRefresh;

    ArrayList<String> globalCases = new ArrayList<>();
    ArrayList<String> globalRecovered = new ArrayList<>();
    ArrayList<String> globalFatal = new ArrayList<>();
    ArrayList<String> globalNewCases = new ArrayList<>();
    ArrayList<String> globalNewDeaths = new ArrayList<>();
    ArrayList<String> updatedTime = new ArrayList<>();

    ArrayList<String> mGlobalCases = new ArrayList<>();
    ArrayList<String> mGlobalRecovered = new ArrayList<>();
    ArrayList<String> mGlobalFatal = new ArrayList<>();
    ArrayList<String> mGlobalNewCases = new ArrayList<>();
    ArrayList<String> mGlobalNewDeaths = new ArrayList<>();
    ArrayList<String> mUpdatedTime = new ArrayList<>();

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
        txtUpdatedTime = findViewById(R.id.txtUpdatedTime);
        btnRefresh = findViewById(R.id.btnRefresh);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        GlobalDataService gs = new GlobalDataService();
        try
        {
            globalCases = gs.getGlobalCases();
            globalRecovered = gs.getGlobalRecovered();
            globalFatal = gs.getGlobalFatal();
            globalNewCases = gs.getGlobalNewCases();
            globalNewDeaths = gs.getGlobalNewDeaths();
            updatedTime = gs.getUpdatedTime();

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
                    wait(400);
                    i++;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        try
        {
            txtConfirmedCases.setText("Confirmed \n" + globalCases.get(0));
            txtRecoveredCases.setText("Recovered \n" + globalRecovered.get(0));
            txtDeathCases.setText("Fatal \n" + globalFatal.get(0));
            txtGlobalNewCases.setText("New cases \n" + globalNewCases.get(0));
            txtGlobalNewDeaths.setText("New deaths \n" + globalNewDeaths.get(0));
            txtUpdatedTime.setText("Last updated \n" + updatedTime.get(0));
        } catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalDataService gsd = new GlobalDataService();
                try
                {
                    mGlobalCases = gsd.getGlobalCases();
                    mGlobalRecovered = gsd.getGlobalRecovered();
                    mGlobalFatal = gsd.getGlobalFatal();
                    mGlobalNewCases = gsd.getGlobalNewCases();
                    mGlobalNewDeaths = gsd.getGlobalNewDeaths();
                    mUpdatedTime = gsd.getUpdatedTime();

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
                    txtConfirmedCases.setText("Confirmed \n" + mGlobalCases.get(0));
                    txtRecoveredCases.setText("Recovered \n" + mGlobalRecovered.get(0));
                    txtDeathCases.setText("Fatal \n" + mGlobalFatal.get(0));
                    txtGlobalNewCases.setText("New cases \n" + mGlobalNewCases.get(0));
                    txtGlobalNewDeaths.setText("New deaths \n" + mGlobalNewDeaths.get(0));
                    txtUpdatedTime.setText("Last updated \n" + mUpdatedTime.get(0));
                } catch (IndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 200);
                toast.show();
            }
        });
    }
}
