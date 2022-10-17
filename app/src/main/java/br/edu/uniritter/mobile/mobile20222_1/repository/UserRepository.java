package br.edu.uniritter.mobile.mobile20222_1.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class UserRepository implements Listener<JSONArray>,Response.ErrorListener{
    private final String TAG = "UserRepository";
    private List<User> users;
    private static UserRepository instance;
    private Context contexto;
    private OnReadyListener onReadyListener;

    private UserRepository(Context contexto) {
        super();
        this.contexto = contexto;
        users = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                null,
                new Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                users.add(createUserFromJson(json));
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

        Log.e(TAG, "UserRepository: lancei" );
    }

    public static UserRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new UserRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getUsers().isEmpty()){
            if (orl != null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }
    // metodo para criar um objeto User apartir de um json
    public User createUserFromJson(JSONObject json) {
        try {
            return new User(json.getInt("id"), json.getString("name"),
                json.getString("username"), json.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<User> getUsers() {
        return users;
    }
    public User getUserById(int id) {
        User ret = null;
        for(User u : users) {
            if (u.getId() == id) {
                ret = u;
            }
        }
        return ret;
    }
    public User getUserByUserLogin(String login) {
        User ret = null;
        Log.d(TAG, "getUserByUserLogin: users.size "+users.size());
        for(User u : users) {
            Log.d(TAG, "getUserByUserLogin: "+login+" ->"+u.getUserLogin());
            if (u.getUserLogin().equals(login)) {
                ret = u;
            }
        }
        return ret;
    }
    public User addUser(User user) {return null;}
    public User updateUser(User user) {return null;}
    public User removeUser(User user) {return null;}


    @Override
    public void onResponse(JSONArray response) {
        Log.e(TAG, "onResponse: "+response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: "+json.toString());
                users.add( new User( json.getInt("id"), json.getString("name"),
                        json.getString("username"), json.getString("username")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if(onReadyListener != null){
            onReadyListener.onReady();
        }
        onReadyListener = null;

        Log.e(TAG, "onResponse: terminei" );
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse: "+error.getMessage() );
    }
    private void teste() {
        Integer a = null;
        int b = 0;

        float c;
        try {
            c = a.floatValue() / b;
        } catch (ArithmeticException e) {
            e.getMessage();
            e.printStackTrace();
            c = 0;
        } catch (NullPointerException npe) {
            c = 0;
        }

        String str = null;
        str.getBytes();


    }
}
