package br.edu.uniritter.mobile.mobile20222_1.view;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.UsersAdapter;
import br.edu.uniritter.mobile.mobile20222_1.databinding.ActivityPhotosBinding;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.OnReadyListener;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PostRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.TodoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserSQLRepository;

public class  MainActivity extends AppCompatActivity implements OnReadyListener {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Busca a lista de TODOS
        TodoRepository.getInstance(this).getTodos();

        //Busca a lista de Posts
        PostRepository.getInstance(this).getPosts();

        //Busca a lista de Albums
        AlbumRepository.getInstance(this).getAlbums();

        //Busca a lista de Photos
        PhotoRepository.getInstance(this).getPhotos();

        User user = getIntent().getParcelableExtra("userObj");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Bem vindo(a) "+user.getName()+"!");

        UserRepository.getInstance(this, this);

        Button btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(view -> {
           getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
           finish();
        });
    }


    @Override
    public void onReady() {
        RecyclerView rc = findViewById(R.id.recycler1);
        List users = UserRepository.getInstance(this, null).getUsers();
        UsersAdapter adapter = new UsersAdapter( users );
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);

        UserSQLRepository.getInstance(getApplicationContext()).saveUsers(users);

        List<User> userTestes = UserSQLRepository.getInstance(getApplicationContext()).getAllUsers();


    }
}