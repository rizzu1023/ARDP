package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public String ip = "192.168.137.1";


    public void loginpage(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    EditText registerpage_textfield_name,registerpage_textfield_username,registerpage_textfield_email,registerpage_textfield_mobile_no;
    EditText registerpage_textfield_gender,registerpage_textfield_password,registerpage_textfield_confirm_password;
    Button registerpage_button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerpage_textfield_name = findViewById(R.id.registerpage_textfield_name);
        registerpage_textfield_username = findViewById(R.id.registerpage_textfield_username);
        registerpage_textfield_email  = findViewById(R.id.registerpage_textfield_email);
        registerpage_textfield_gender = findViewById(R.id.registerpage_textfield_gender);
        registerpage_textfield_mobile_no = findViewById(R.id.registerpage_textfield_mobile_no);
        registerpage_textfield_password = findViewById(R.id.registerpage_textfield_password);
        registerpage_textfield_confirm_password = findViewById(R.id.registerpage_textfield_confirm_password);
        registerpage_button_signup = findViewById(R.id.registerpage_button_signup);


        registerpage_button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    register();
            }
        });
    }

    public void register(){

        String registerAPI = "http://"+ip+"/api/register";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("2")){
                            Toast.makeText(getApplicationContext(),"Password doesn't match!",Toast.LENGTH_SHORT).show();
                        }
                        if(response.contains("3")){
                            Toast.makeText(getApplicationContext(),"This username is not available!",Toast.LENGTH_SHORT).show();
                        }
                        if(response.contains("0")){
                            Toast.makeText(getApplicationContext(),"This Email Address already in used",Toast.LENGTH_SHORT).show();
                        }
                        if(response.contains("1")){
                            Toast.makeText(getApplicationContext(),"You are successfuly Registerd",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.toString());
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",registerpage_textfield_name.getText().toString());
                params.put("username",registerpage_textfield_username.getText().toString());
                params.put("email",registerpage_textfield_email.getText().toString());
                params.put("gender",registerpage_textfield_gender.getText().toString());
                params.put("mobile_no",registerpage_textfield_mobile_no.getText().toString());
                params.put("password",registerpage_textfield_password.getText().toString());
                params.put("cpassword",registerpage_textfield_confirm_password.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
