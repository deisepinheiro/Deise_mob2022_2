package br.edu.uniritter.mobile.mobile20222_1.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter{
    private List<Comment> comments;

    public CommentAdapter(List<Comment> albums) {
        this.comments = albums;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_vh,parent,false);
        return new CommentViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        ((TextView) holder.itemView.findViewById(R.id.txv_id)).setText(comment.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.txv_nome)).setText(comment.getName());
        ((TextView) holder.itemView.findViewById(R.id.txv_email)).setText(comment.getEmail());
        ((TextView) holder.itemView.findViewById(R.id.txv_body)).setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}

class CommentViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
