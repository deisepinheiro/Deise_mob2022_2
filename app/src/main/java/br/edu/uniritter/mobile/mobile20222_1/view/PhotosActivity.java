package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.AlbumAdapter;
import br.edu.uniritter.mobile.mobile20222_1.adapter.PhotoAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityPhotosBinding;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityPostsBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.Photo;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.presenter.CommentPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.CommentPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PhotosPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PhotosPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;

public class PhotosActivity extends AppCompatActivity implements PhotosPresenterContract.view {

    private final String TAG = "PostsActivity";
    private ActivityPhotosBinding layout;
    private PhotosPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = DataBindingUtil.setContentView(this, R.layout.activity_photos);
        Album album = getIntent().getParcelableExtra("album");
        layout.setAlbum(album);

        this.presenter = new PhotosPresenter(this);
        presenter.getPhotos(album.getId());

    }

    @Override
    public void message(String msg) {
        Snackbar.make(this,layout.textView3,
                msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void listItens(List<Photo> photos) {
        PhotoAdapter adapter = new PhotoAdapter(photos);
        layout.recPhotos.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        layout.recPhotos.setLayoutManager(llm1);

    }
}