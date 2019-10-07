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



    public static String name;
    public static String email;
    public static String gender;
    public static String mobile_no;
    public static String password;

    public void loginpage(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    EditText registerpage_textfield_name,registerpage_textfield_email,registerpage_textfield_mobile_no;
    EditText registerpage_textfield_gender,registerpage_textfield_password,registerpage_textfield_confirm_password;
    Button registerpage_button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerpage_textfield_name = findViewById(R.id.registerpage_textfield_name);
        registerpage_textfield_email  = findViewById(R.id.registerpage_textfield_email);
        registerpage_textfield_gender = findViewById(R.id.registerpage_textfield_gender);
        registerpage_textfield_mobile_no = findViewById(R.id.registerpage_textfield_mobile_no);
        registerpage_textfield_password = findViewById(R.id.registerpage_textfield_password);
        registerpage_textfield_confirm_password = findViewById(R.id.registerpage_textfield_confirm_password);
        registerpage_button_signup = findViewById(R.id.registerpage_button_signup);


        registerpage_button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(registerpage_textfield_name.getText().toString().matches("") || registerpage_textfield_email.getText().toString().matches("") || registerpage_textfield_gender.getText().toString().matches("") || registerpage_textfield_mobile_no.getText().toString().matches("") || registerpage_textfield_password.getText().toString().matches("") || registerpage_textfield_confirm_password.getText().toString().matches("")){
                        Toast.makeText(RegisterActivity.this, "Please insert all Data!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {  if(registerpage_textfield_password.getText().toString().equals(registerpage_textfield_confirm_password.getText().toString()) ) {
                        name = registerpage_textfield_name.getText().toString();
                        email = registerpage_textfield_email.getText().toString();
                        gender = registerpage_textfield_gender.getText().toString();
                        mobile_no = registerpage_textfield_mobile_no.getText().toString();
                        password = registerpage_textfield_password.getText().toString();

                        register();
                    }
                        else{
                            Toast.makeText(RegisterActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });
    }

    public void register(){

        String registerAPI = "http://"+MainActivity.ip+"/api/emailExistOrNot";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.contains("0")){
                            Toast.makeText(getApplicationContext(),"This Email Address already in used",Toast.LENGTH_SHORT).show();
                        }
                        if(response.contains("1")){
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Register2Activity.class));
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
                params.put("email",registerpage_textfield_email.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
