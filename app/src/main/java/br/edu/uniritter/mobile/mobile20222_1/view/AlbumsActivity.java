package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.AlbumAdapter;
import br.edu.uniritter.mobile.mobile20222_1.adapter.PostAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityAlbunsBinding;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityPostsBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.presenter.AlbumPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.AlbumsPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PostRepository;

public class AlbumsActivity extends AppCompatActivity implements AlbumsPresenterContract.view {

    private final String TAG = "AlbumsActivity";
    private ActivityAlbunsBinding layout;
    private AlbumsPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new AlbumPresenter(this);

        layout = DataBindingUtil.setContentView(this, R.layout.activity_albuns);
        User user = getIntent().getParcelableExtra("user");
        layout.setUser(user);

        presenter.getAlbums(user.getId());

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
    public void listItens(List<Album> albuns) {
        AlbumAdapter adapter = new AlbumAdapter( albuns);
        layout.recAlbums.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        layout.recAlbums.setLayoutManager(llm1);
    }
}