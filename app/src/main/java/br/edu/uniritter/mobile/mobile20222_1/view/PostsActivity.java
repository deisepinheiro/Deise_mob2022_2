package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.PostAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityPostsBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PhotosPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PhotosPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PostsPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PostsPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.CommentRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PostRepository;


public class PostsActivity extends AppCompatActivity implements PostsPresenterContract.view{

    private final String TAG = "PostsActivity";
    private ActivityPostsBinding layout;
    private PostsPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommentRepository.getInstance(this).getComments();

        layout = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        User user = getIntent().getParcelableExtra("user");
        layout.setUser(user);

        this.presenter = new PostsPresenter(this);
        presenter.getPosts(user.getId());

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
    public void listItens(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(posts);
        layout.recPosts.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        layout.recPosts.setLayoutManager(llm1);
    }
}