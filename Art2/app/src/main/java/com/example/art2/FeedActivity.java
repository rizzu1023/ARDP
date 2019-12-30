package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {


    public void profilepage(View view){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final RecyclerView post_list = findViewById(R.id.post_list);
        post_list.setLayoutManager(new LinearLayoutManager(this));
//        String[] feeds = {"abcd" , "efgh" , "ijkl" , "mnop" , "qrst" , "uvwx"};


        String FeedAPI = "http://"+MainActivity.ip+"/api/feed";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FeedAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("CODE", response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        UserDetail[] data = gson.fromJson(response, UserDetail[].class);
                        post_list.setAdapter(new PostListAdapter(FeedActivity.this, data));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR",error.toString());
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
