package com.example.localevents;

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

    public ArrayList<String> getTitle()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return titles;
    }

    public ArrayList<String> getDescription()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                    e.printStackTrace();
                }
            }
        });
        return descriptions;
    }

    public ArrayList<String> getContent()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                    e.printStackTrace();
                }
            }
        });
        return contents;
    }

    public ArrayList<String> getUrl()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                    e.printStackTrace();
                }
            }
        });
        return urls;
    }

    public ArrayList<String> getPublishedDate()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                    e.printStackTrace();
                }
            }
        });
        return dates;
    }

    public ArrayList<String> getImageUrl()
    {
        Request request = new Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?q=coronavirus&country=us&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
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
                    e.printStackTrace();
                }
            }
        });
        return imageUrls;
    }
}
