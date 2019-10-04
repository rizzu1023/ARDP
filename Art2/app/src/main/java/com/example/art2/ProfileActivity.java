package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LongSummaryStatistics;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    String username;
    TextView profilepage_textview_username;

    public void feedpage(View view){
        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String ProfileAPI = "http://"+MainActivity.ip+"/api/profile";

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ProfileAPI, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("thisrizzu",response.getString("username"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//
//                params.put("usernameOrEmail",MainActivity.usernameOrEmail);
//
//                return params;
//            }
//        };

        profilepage_textview_username = findViewById(R.id.profilepage_textview_username);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProfileAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            username = object.getString("username");
                            profilepage_textview_username.setText(username);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("usernameOrEmail",MainActivity.usernameOrEmail);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
