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
import br.edu.uniritter.mobile.mobile20222_1.model.Post;

public class AlbumRepository {

    private final String TAG = "AlbumsRepository";
    private List<Album> albums;
    private static AlbumRepository instance;
    private Context contexto;

    public List<Album> getAlbums() {
        return albums;
    }

    private AlbumRepository(Context contexto) {
        super();
        this.contexto = contexto;
        albums = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/albums",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                albums.add(createTodoFromJson(json));
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
    public Album createTodoFromJson(JSONObject json) {
        try {
            return new Album(json.getInt("userId"), json.getInt("id"),
                    json.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AlbumRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new AlbumRepository(contexto);
        }
        return instance;
    }

    public List<Album> getAlbumsbyUser(int id) {
        List<Album> albumsUser = new ArrayList<>();
        for (Album a: albums) {
            if (a.getUserId() == id){
                albumsUser.add(a);
            }
        }
        return albumsUser;
    }
}
