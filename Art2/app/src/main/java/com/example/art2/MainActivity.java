package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String ip = "192.168.43.4";

    public static String usernameOrEmail;

    public Button loginpage_button_login;
    public EditText loginpage_textfield_email;
    public EditText loginpage_textfield_password;


    public void registerpage(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void profile(View view){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginpage_button_login = findViewById(R.id.loginpage_button_login);
        loginpage_textfield_email = findViewById(R.id.loginpage_textfield_email);
        loginpage_textfield_password = findViewById(R.id.loginpage_textfield_password);



        loginpage_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginpage_textfield_email.getText().toString().matches("") || loginpage_textfield_password.getText().toString().matches(""))
                    Toast.makeText(MainActivity.this, "Please insert all the data", Toast.LENGTH_SHORT).show();
                else
                    login();
            }
        });

    }


        public void login() {

            String loginAPI = "http://"+ip+"/api/login";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, loginAPI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                if(response.contains("1")){
                                    Toast.makeText(getApplicationContext(),
                                            "You are succesfully Logged in." , Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),
                                            "Username or Password is incorrect." , Toast.LENGTH_SHORT).show();
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

                    usernameOrEmail = loginpage_textfield_email.getText().toString();

                    params.put("email",loginpage_textfield_email.getText().toString());
                    params.put("password",loginpage_textfield_password.getText().toString());
                    return params;
                }
            };


            stringRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });

            Volley.newRequestQueue(this).add(stringRequest);
        }
    }




