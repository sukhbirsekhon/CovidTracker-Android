package com.example.localevents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataService
{
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
            .get()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
            .build();

    public ArrayList<String> getCountryName() throws IOException, JSONException
    {
        final ArrayList<String> countries = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("country_name");
                        countries.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return countries;
    }

    public List<String> getNumberOfCases() throws IOException, JSONException
    {
        final List<String> cases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("cases");
                        cases.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return cases;
    }

    public List<String> getNumberOfDeaths() throws IOException, JSONException
    {
        final List<String> deaths = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("deaths");
                        deaths.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return deaths;
    }

    public List<String> getTotalRecovered() throws IOException, JSONException
    {
        final List<String> totalRecovered = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("total_recovered");
                        totalRecovered.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return totalRecovered;
    }

    public List<String> getNewDeaths() throws IOException, JSONException
    {
        final List<String> newDeaths = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("new_deaths");
                        newDeaths.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return newDeaths;
    }

    public List<String> getNewCases() throws IOException, JSONException
    {
        final List<String> newCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("new_cases");
                        newCases.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return newCases;
    }

    public List<String> getSeriousCritical() throws IOException, JSONException
    {
            final List<String> seriousCritical = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("serious_critical");
                        seriousCritical.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return seriousCritical;
    }

    public List<String> getActiveCases() throws IOException, JSONException
    {
        final List<String> activeCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("active_cases");
                        activeCases.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return activeCases;
    }

    public ArrayList<String> getTotalCasesPerMillionPopulation() throws IOException, JSONException
    {
        final ArrayList<String> totalCasePerMillion = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String casesByCountry = response.body().string();

                    JSONObject Jobject = new JSONObject(casesByCountry);
                    JSONArray Jarray = Jobject.getJSONArray("countries_stat");

                    for (int i = 0; i < Jarray.length(); i++) {
                        String object = Jarray.getJSONObject(i).getString("total_cases_per_1m_population");
                        totalCasePerMillion.add(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return totalCasePerMillion;
    }
}
