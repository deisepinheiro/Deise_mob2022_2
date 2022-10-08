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
import br.edu.uniritter.mobile.mobile20222_1.adapter.TodoAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.TodoActivityBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.Todo;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PostsPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.PostsPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.presenter.ToDoPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.ToDoPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.TodoRepository;

public class ToDoActivity extends AppCompatActivity implements ToDoPresenterContract.view{
    private final String TAG = "TodoActivity";
    private TodoActivityBinding layout;
    private ToDoPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = DataBindingUtil.setContentView(this, R.layout.todo_activity);
        User user = getIntent().getParcelableExtra("user");
        layout.setUser(user);

        this.presenter = new ToDoPresenter(this);
        presenter.getTodos(user.getId());


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
    public void listItens(List<Todo> todos) {
        TodoAdapter adapter = new TodoAdapter(todos);
        layout.recTodo.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        layout.recTodo.setLayoutManager(llm1);

    }
}