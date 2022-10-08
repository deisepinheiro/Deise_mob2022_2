package br.edu.uniritter.mobile.mobile20222_1.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Post;

public class PostRepository {

    private final String TAG = "PostRepository";
    private List<Post> posts;
    private static PostRepository instance;
    private Context contexto;

    public List<Post> getPosts() {
        return posts;
    }

    private PostRepository(Context contexto) {
        super();
        this.contexto = contexto;
        posts = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                posts.add(createTodoFromJson(json));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e(TAG, "onResponse: terminei");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });

        queue.add(jaRequestInject1);
    }

    // metodo para criar um objeto User apartir de um json
    public Post createTodoFromJson(JSONObject json) {
        try {
            return new Post(json.getInt("userId"), json.getInt("id"),
                    json.getString("title"), json.getString("body"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PostRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new PostRepository(contexto);
        }
        return instance;
    }

    public List<Post> getPostsbyUser(int id) {
        List<Post> postsUser = new ArrayList<>();
        for (Post p: posts) {
            if (p.getUserId() == id){
                postsUser.add(p);
            }
        }
        return postsUser;
    }
}
