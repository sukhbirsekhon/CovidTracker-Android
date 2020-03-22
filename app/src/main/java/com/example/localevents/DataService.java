package com.example.localevents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    public JSONArray getCountryName() throws IOException, JSONException
    {
        final JSONArray countries = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("country_name");
                        countries.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return countries;
    }

    public JSONArray getNumberOfCases() throws IOException, JSONException
    {
        final JSONArray cases = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("cases");
                        cases.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return cases;
    }

    public JSONArray getNumberOfDeaths() throws IOException, JSONException
    {
        final JSONArray deaths = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("deaths");
                        deaths.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return deaths;
    }

    public JSONArray getTotalRecovered() throws IOException, JSONException
    {
        final JSONArray totalRecovered = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("total_recovered");
                        totalRecovered.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return totalRecovered;
    }

    public JSONArray getNewDeaths() throws IOException, JSONException
    {
        final JSONArray newDeaths = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("new_deaths");
                        newDeaths.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return newDeaths;
    }

    public JSONArray getNewCases() throws IOException, JSONException
    {
        final JSONArray newCases = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("new_cases");
                        newCases.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return newCases;
    }

    public JSONArray getSeriousCritical() throws IOException, JSONException
    {
        final JSONArray seriousCritical = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("serious_critical");
                        seriousCritical.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return seriousCritical;
    }

    public JSONArray getActiveCases() throws IOException, JSONException
    {
        final JSONArray activeCases = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("active_cases");
                        activeCases.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return activeCases;
    }

    public JSONArray getTotalCasesPerMillionPopulation() throws IOException, JSONException
    {
        final JSONArray totalCasePerMillion = new JSONArray();

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

                    Object o = null;

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        o = object.get("total_cases_per_1m_population");
                        totalCasePerMillion.put(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return totalCasePerMillion;
    }
}
