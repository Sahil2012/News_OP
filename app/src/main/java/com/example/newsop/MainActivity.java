package com.example.newsop;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsAct {

    private NewsAdapter mna;
    String url = "https://saurav.tech/NewsAPI/top-headlines/category/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String st = getIntent().getStringExtra("str");
        url = url + st + "/in.json";

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mna = new NewsAdapter(MainActivity.this);
        getData();

        rv.setAdapter(mna);

    }

    void getData() {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray att = response.getJSONArray("articles");
                            ArrayList<News> narr = new ArrayList<>();

                            for(int i = 0; i < att.length();i++) {
                                JSONObject temp = att.getJSONObject(i);

                                News a = new News(temp.getString("title"),temp.getString("urlToImage"),temp.getString("url"),temp.getString("content"));
                                narr.add(a);
                            }
                            mna.getMe(narr);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error in parsing results", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error in parsing results", Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void onClickNews(String s) {

        CustomTabsIntent.Builder b = new CustomTabsIntent.Builder();
        CustomTabsIntent it = b.build();
        it.launchUrl(MainActivity.this, Uri.parse(s));
//        Toast.makeText(MainActivity.this,"hello i am toast",Toast.LENGTH_LONG).show();
    }
}