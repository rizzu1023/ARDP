package com.example.art2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView post_list = findViewById(R.id.post_list);
        post_list.setLayoutManager(new LinearLayoutManager(this));
        String[] feeds = {"abcd" , "efgh" , "ijkl" , "mnop" , "qrst" , "uvwx"};
        post_list.setAdapter(new PostListAdapter(feeds));
    }
}
