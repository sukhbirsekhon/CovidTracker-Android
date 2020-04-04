package covid19.trakcker.localevents;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GlobalDataService
{
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
            .get()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
            .build();

    public ArrayList<String> getGlobalCases() throws IOException, JSONException
    {
        final ArrayList<String> globalCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("total_cases");
                    String s = (String) o;
                    globalCases.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return globalCases;
    }

    public ArrayList<String> getGlobalRecovered() throws IOException, JSONException
    {
        final ArrayList<String> globalRecoveredCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("total_recovered");
                    String s = (String) o;
                    globalRecoveredCases.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return globalRecoveredCases;
    }

    public ArrayList<String> getGlobalFatal() throws IOException, JSONException
    {
        final ArrayList<String> globalFatalCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("total_deaths");
                    String s = (String) o;
                    globalFatalCases.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return globalFatalCases;
    }

    public ArrayList<String> getGlobalNewCases() throws IOException, JSONException
    {
        final ArrayList<String> globalNewCases = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("new_cases");
                    String s = (String) o;
                    globalNewCases.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return globalNewCases;
    }

    public ArrayList<String> getGlobalNewDeaths() throws IOException, JSONException
    {
        final ArrayList<String> globalNewDeaths = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("new_deaths");
                    String s = (String) o;
                    globalNewDeaths.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return globalNewDeaths;
    }

    public ArrayList<String> getUpdatedTime() throws IOException, JSONException
    {
        final ArrayList<String> updatedTime = new ArrayList<>();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    String worldStat = response.body().string();

                    JSONObject Jobject = new JSONObject(worldStat);
                    Object o = Jobject.get("statistic_taken_at");
                    String s = (String) o;
                    updatedTime.add(s);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return updatedTime;
    }
}
