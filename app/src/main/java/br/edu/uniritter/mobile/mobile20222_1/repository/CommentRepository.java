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

import br.edu.uniritter.mobile.mobile20222_1.model.Album;
import br.edu.uniritter.mobile.mobile20222_1.model.Comment;

public class CommentRepository {

    private final String TAG = "CommentsRepository";
    private List<Comment> comments;
    private static CommentRepository instance;
    private Context contexto;

    public List<Comment> getComments() {
        return comments;
    }

    private CommentRepository(Context contexto) {
        super();
        this.contexto = contexto;
        comments = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/comments",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                comments.add(createCommentFromJson(json));
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

    // metodo para criar um objeto apartir de um json
    public Comment createCommentFromJson(JSONObject json) {
        try {
            return new Comment(json.getInt("postId"), json.getInt("id"),
                    json.getString("name"),json.getString("email"),json.getString("body"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CommentRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new CommentRepository(contexto);
        }
        return instance;
    }

    public List<Comment> getCommentsByPost(int id) {
        List<Comment> commentsPost = new ArrayList<>();
        for (Comment c: comments) {
            if (c.getPostId() == id){
                commentsPost.add(c);
            }
        }
        return commentsPost;
    }
}
