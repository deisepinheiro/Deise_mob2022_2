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

import br.edu.uniritter.mobile.mobile20222_1.model.Todo;

public class TodoRepository {

    private final String TAG = "TodoRepository";
    private List<Todo> todos;
    private static TodoRepository instance;
    private Context contexto;

    public List<Todo> getTodos() {
        return todos;
    }

    private TodoRepository(Context contexto) {
        super();
        this.contexto = contexto;
        todos = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(contexto);

        //exemplo de uso com injeção do ResponseListener e erro Listener
        JsonArrayRequest jaRequestInject1 = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, "onResponse: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + json.toString());
                                todos.add(createTodoFromJson(json));
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

        Log.e(TAG, "TodoRepository: lancei" );
    }

    // metodo para criar um objeto User apartir de um json
    public Todo createTodoFromJson(JSONObject json) {
        try {
            return new Todo(json.getInt("userId"), json.getInt("id"),
                    json.getString("title"), json.getBoolean("completed"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TodoRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new TodoRepository(contexto);
        }
        return instance;
    }

    public List<Todo> getUserTodos(int id) {
        List<Todo> todosUser = new ArrayList<>();
        for (Todo t: todos) {
            if (t.getUserId() == id){
                todosUser.add(t);
            }
        }
        return todosUser;
    }
}
