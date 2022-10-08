package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.adapter.UsersAdapter;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.repository.AlbumRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PhotoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.PostRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.TodoRepository;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;

public class MainActivity extends AppCompatActivity {
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
        tv.setText("Bem vindo "+user.getName()+"!");

        RecyclerView rc = findViewById(R.id.recycler1);
        UsersAdapter adapter = new UsersAdapter( UserRepository.getInstance(this).getUsers());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);






    }


}