package com.example.localevents;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewDataService
{
    OkHttpClient client = new OkHttpClient();

    public ArrayList<String> getTitle(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> titles = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String title = response.body().string();

                    JSONObject Jobject = new JSONObject(title);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("title");
                        titles.add(object);
                    }
                } catch (JSONException e)
                {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return titles;
    }

    public ArrayList<String> getDescription(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> descriptions = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String description = response.body().string();

                    JSONObject Jobject = new JSONObject(description);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("description");
                        descriptions.add(object);
                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return descriptions;
    }

    public ArrayList<String> getContent(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> contents = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String o = response.body().string();

                    JSONObject Jobject = new JSONObject(o);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("content");
                        contents.add(object);
                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return contents;
    }

    public ArrayList<String> getUrl(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> urls = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String o = response.body().string();

                    JSONObject Jobject = new JSONObject(o);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("url");
                        urls.add(object);
                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return urls;
    }

    public ArrayList<String> getPublishedDate(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> dates = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String o = response.body().string();

                    JSONObject Jobject = new JSONObject(o);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("publishedAt");
                        dates.add(object);
                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return dates;
    }

    public ArrayList<String> getImageUrl(String country, final Context c)
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country="+ country +"&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
                .get()
                .build();

        final ArrayList<String> imageUrls = new ArrayList<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    String o = response.body().string();

                    JSONObject Jobject = new JSONObject(o);
                    JSONArray Jarray = Jobject.getJSONArray("articles");

                    for (int i = 0; i <Jarray.length(); i++)
                    {
                        String object = Jarray.getJSONObject(i).getString("urlToImage");
                        imageUrls.add(object);
                    }
                } catch (JSONException e) {
                    Toast toast = Toast.makeText(c,"New is unavailable. Come back later.",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 200);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
        return imageUrls;
    }
}
