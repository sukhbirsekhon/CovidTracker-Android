package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Home extends AppCompatActivity
{
    OkHttpClient client = new OkHttpClient();
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

        result = findViewById(R.id.txtResult);

        callAPI = findViewById(R.id.btnAPI);
        callAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getCovidWebService();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        client = new OkHttpClient();
    }

    private void getDataForCountryAffected() {
        Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/affected.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
                .build();
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Fail to get country affected data");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            result.setText(response.body().string());
                        } catch (IOException ioe){
                            result.setText("Error on calling API");
                        }
                    }
                });
            }
        });
    }
    private void getCovidWebService() throws IOException {
        Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/usastates.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Failure");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            result.setText(response.body().string());
                        } catch (IOException ioe){
                            result.setText("Error on calling API");
                        }
                    }
                });
            }
        });
    }
}
