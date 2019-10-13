package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView profilepage_image_dp;

    public void feedpage(View view){
        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
    }

    public void unityOpenScene(View view){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.Four.ARt");
        startActivity(intent);
    }

    public Bitmap StringToBitmap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
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
        profilepage_image_dp = findViewById(R.id.profilepage_image_dp);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProfileAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            username = object.getString("username");
                            String image_url = object.getString("image_url");
                            profilepage_image_dp.setImageBitmap(StringToBitmap(image_url));
                            profilepage_textview_username.setText(username);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                if(MainActivity.usernameOrEmail.equals(""))
                params.put("usernameOrEmail", RegisterActivity.usernameOrEmail);
                else
                params.put("usernameOrEmail",MainActivity.usernameOrEmail);


                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }


}
