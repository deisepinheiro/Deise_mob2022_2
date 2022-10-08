package br.edu.uniritter.mobile.mobile20222_1.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.view.CommentActivity;
import br.edu.uniritter.mobile.mobile20222_1.view.PhotosActivity;
import br.edu.uniritter.mobile.mobile20222_1.view.PostsActivity;

public class PostAdapter extends RecyclerView.Adapter{

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_vh,parent,false);
        return new PostViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post post = posts.get(position);
        ((TextView) holder.itemView.findViewById(R.id.txv_id)).setText(post.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.txv_title)).setText(post.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.txv_body)).setText(post.getBody());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CommentActivity.class);
            intent.putExtra("post",post);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}

class PostViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}