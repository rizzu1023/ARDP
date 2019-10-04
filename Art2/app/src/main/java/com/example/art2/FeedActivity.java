package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FeedActivity extends AppCompatActivity {

    public void profilepage(View view){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }
}
