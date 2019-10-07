import android.view.View;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class PostListViewHolder extends RecyclerView.ViewHolder{

        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
