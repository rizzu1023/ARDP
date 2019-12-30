package com.example.art2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private UserDetail[] data;
    private Context context;
    public PostListAdapter(Context context, UserDetail[] data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_list_layout, parent, false);
        return new PostListViewHolder(view);
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
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        UserDetail detail = data[position];
        holder.feedpage_textview_username.setText(detail.username);
        holder.feedpage_image_post.setImageBitmap(StringToBitmap(detail.image_url));

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class PostListViewHolder extends RecyclerView.ViewHolder{
        ImageView feedpage_image_post;
        TextView feedpage_textview_username;
        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);
            feedpage_image_post = itemView.findViewById(R.id.feedpage_image_post);
            feedpage_textview_username = itemView.findViewById(R.id.feedpage_textview_username);
        }
    }
}
