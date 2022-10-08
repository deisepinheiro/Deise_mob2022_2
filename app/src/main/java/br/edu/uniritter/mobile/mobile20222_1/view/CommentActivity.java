package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.CommentAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityCommentBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.Comment;
import br.edu.uniritter.mobile.mobile20222_1.model.Post;
import br.edu.uniritter.mobile.mobile20222_1.presenter.CommentPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.CommentPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.CommentRepository;

public class CommentActivity extends AppCompatActivity implements CommentPresenterContract.view {

    private final String TAG = "CommentActivity";
    private ActivityCommentBinding layout;
    private CommentPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        Post post = getIntent().getParcelableExtra("post");
        layout.setPost(post);

        this.presenter = new CommentPresenter(this);
        presenter.getComments(post.getId());
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
    public void listItens(List<Comment> comments) {
        CommentAdapter adapter = new CommentAdapter(comments);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        layout.recComments.setLayoutManager(llm1);
        layout.recComments.setAdapter(adapter);

    }
}