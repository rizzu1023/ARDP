package com.example.art2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private String[] data;
    public PostListAdapter(String[] data){
        this.data = data;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_list_layout, parent, false);
        return new PostListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        String username = data[position];
        holder.feedpage_textview_username.setText(username);
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
