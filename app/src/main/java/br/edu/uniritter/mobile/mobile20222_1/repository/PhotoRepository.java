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

import br.edu.uniritter.mobile.mobile20222_1.model.Photo;


public class PhotoRepository {

    private final String TAG = "PhotoRepository";
    private List<Photo> photos;
    private static PhotoRepository instance;
    private Context contexto;

    public List<Photo> getPhotos() {
        return photos;
    }

    private PhotoRepository(Context contexto) {
        super();
        this.contexto = contexto;
        photos = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/photos",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                photos.add(createTodoFromJson(json));
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
    public Photo createTodoFromJson(JSONObject json) {
        try {
            return new Photo(json.getInt("albumId"), json.getInt("id"),
                    json.getString("title"),json.getString("url"),json.getString("thumbnailUrl"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PhotoRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new PhotoRepository(contexto);
        }
        return instance;
    }

    public List<Photo> getPhotobyAlbum(int id) {
        List<Photo> photosAlbum = new ArrayList<>();
        for (Photo p: photos) {
            if (p.getAlbumId() == id){
                photosAlbum.add(p);
            }
        }
        return photosAlbum;
    }
}
