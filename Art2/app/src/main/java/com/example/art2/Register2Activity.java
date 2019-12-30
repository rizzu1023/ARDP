package com.example.art2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register2Activity extends AppCompatActivity {

    public Bitmap bitmap;

    Button register2page_button_submit;
    EditText register2page_textfield_username;

    private CircleImageView ProfileImage;


    private static final int PICK_IMAGE = 1;
    Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        register2page_button_submit = findViewById(R.id.register2page_button_submit);
        register2page_textfield_username = findViewById(R.id.register2page_textfield_username);
        ProfileImage = findViewById(R.id.register2page_image_profile);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Image"), PICK_IMAGE);

            }
        });

        register2page_button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register2();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            ImageUri = data.getData();
            try {
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
                ProfileImage.setImageBitmap(bitmap);

//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
//                byte[] imagebyte = stream.toByteArray();
                // Bitmap imageCompressed = BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.length);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
        byte[] imagebyte = stream.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);

        return encode;
    }


    public void register2() {

        String image = imageToString(bitmap);
        Integer count = image.length();
        Log.d("rizzuthis", String.valueOf(count));
//        Log.d("rizzuthis2",image.getClass().getName());
        String registerAPI = "http://" + MainActivity.ip + "/api/register";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        String r = response.trim();
//                        Toast.makeText(getApplicationContext(),r,Toast.LENGTH_LONG).show();
//                          Log.d("rizzuthis3",response);

                        if (response.contains("0")) {
                            Toast.makeText(getApplicationContext(), "Username is not Available!", Toast.LENGTH_SHORT).show();
                        }
                        if (response.contains("1")) {
                            Toast.makeText(getApplicationContext(), "You are Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null) {
                            Log.d("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                        }
                        if (error instanceof TimeoutError) {
                            Log.e("Volley", "TimeoutError");
                        } else if (error instanceof NoConnectionError) {
                            Log.d("Volley", "NoConnectionError");
                        } else if (error instanceof AuthFailureError) {
                            Log.d("Volley", "AuthFailureError");
                        } else if (error instanceof ServerError) {
                            Log.d("Volley", "ServerError");
                        } else if (error instanceof NetworkError) {
                            Log.d("Volley", "NetworkError");
                        } else if (error instanceof ParseError) {
                            Log.d("Volley", "ParseError");
                        }
                        Log.e("Error", error.toString());
                        Toast.makeText(Register2Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                String image = imageToString(bitmap);

                Map<String, String> params = new HashMap<>();
                params.put("username", register2page_textfield_username.getText().toString());
                params.put("name", RegisterActivity.name);
                params.put("email", RegisterActivity.email);
                params.put("gender", RegisterActivity.gender);
                params.put("mobile_no", RegisterActivity.mobile_no);
                params.put("password", RegisterActivity.password);
                params.put("image_url", imageToString(bitmap));
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
