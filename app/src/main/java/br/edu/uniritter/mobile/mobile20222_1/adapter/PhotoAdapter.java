package br.edu.uniritter.mobile.mobile20222_1.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.Photo;


public class PhotoAdapter  extends RecyclerView.Adapter{
    private List<Photo> photos;

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photo_vh,parent,false);
        return new PhotoViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        ((TextView) holder.itemView.findViewById(R.id.txv_id)).setText(photo.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.txv_title)).setText(photo.getTitle());
        ImageView image =  holder.itemView.findViewById(R.id.img_view);

        Picasso.get()
                .load(photo.getThumbnailUrl()+".jpg")
                .into(image);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}

class PhotoViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
