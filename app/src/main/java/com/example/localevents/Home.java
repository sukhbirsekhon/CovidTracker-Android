package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
{
    Button logout, callAPI, btnCasesByCountry;
    TextView txtConfirmedCases, txtRecoveredCases, txtDeathCases;
    ImageButton btnRefresh;

    ArrayList<String> globalCases = new ArrayList<>();
    ArrayList<String> globalRecovered = new ArrayList<>();
    ArrayList<String> globalFatal = new ArrayList<>();

    ArrayList<String> mGlobalCases = new ArrayList<>();
    ArrayList<String> mGlobalRecovered = new ArrayList<>();
    ArrayList<String> mGlobalFatal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        callAPI = findViewById(R.id.btnAPI);
        btnCasesByCountry = findViewById(R.id.btnCasesByCountry);
        logout = findViewById(R.id.btnLogoutH);
        txtConfirmedCases = findViewById(R.id.txtConfirmedCases);
        txtRecoveredCases = findViewById(R.id.txtRecoveredCases);
        txtDeathCases = findViewById(R.id.txtDeathCases);
        btnRefresh = findViewById(R.id.btnRefresh);

        GlobalDataService gs = new GlobalDataService();
        try
        {
            globalCases = gs.getGlobalCases();
            globalRecovered = gs.getGlobalRecovered();
            globalFatal = gs.getGlobalFatal();

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
        System.out.println("*******0 " + globalCases);
        try
        {
            txtConfirmedCases.setText("Confirmed \n" + globalCases.get(0));
            txtRecoveredCases.setText("Recovered \n" + globalRecovered.get(0));
            txtDeathCases.setText("Fatal \n" + globalFatal.get(0));
        } catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

        btnCasesByCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*******03 " + globalCases);
                startActivity(new Intent(getApplicationContext(), CasesByCountry.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalDataService gsd = new GlobalDataService();
                try
                {
                    mGlobalCases = gsd.getGlobalCases();
                    mGlobalRecovered = gsd.getGlobalRecovered();
                    mGlobalFatal = gsd.getGlobalFatal();

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
                System.out.println("*****************************************8refre " + mGlobalCases);
                try
                {
                    txtConfirmedCases.setText("Confirmed \n" + mGlobalCases.get(0));
                    txtRecoveredCases.setText("Recovered \n" + mGlobalRecovered.get(0));
                    txtDeathCases.setText("Fatal \n" + mGlobalFatal.get(0));
                } catch (IndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(getApplicationContext(),"Refreshed",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 200);
                toast.show();
            }
        });
    }
}
