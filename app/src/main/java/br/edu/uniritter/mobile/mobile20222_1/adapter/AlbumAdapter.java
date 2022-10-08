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
import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.view.PhotosActivity;

public class AlbumAdapter extends RecyclerView.Adapter{
    private List<Album> albums;

    public AlbumAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_album_vh,parent,false);
        return new AlbumViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = albums.get(position);
        ((TextView) holder.itemView.findViewById(R.id.txv_id)).setText(album.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.txv_title)).setText(album.getTitle());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PhotosActivity.class);
            intent.putExtra("album",album);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}

class AlbumViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
