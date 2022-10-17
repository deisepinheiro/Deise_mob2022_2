package br.edu.uniritter.mobile.mobile20222_1.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.Todo;
import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class TodoSQLRepository {

    private final String TAG = "TodoSQLRepository";
    private List<Todo> todos;
    private static TodoSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;


    public static TodoSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new TodoSQLRepository(contexto);
        }
        return instance;
    }

    private TodoSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public List<Todo> getAllTodosFromuser(int uId){
        todos = new ArrayList<>();
        String sql = "select userId, id, title, completed from todos where userId="+uId;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor != null && cursor.moveToFirst()) {
            do{
                Todo todo = new Todo(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)==1);
                todos.add(todo);
            }while (cursor.moveToNext());
        }

        return todos;
    }

    public void saveTodos(List<Todo> todos){
        //for (int i = 0; i < todos.size(); i++) {
        for (Todo t : todos) {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put("userId", t.getUserId() );
            values.put("id", t.getId() );
            values.put("title", t.getTitle() );
            values.put("completed", t.isCompleted() ? 1:0);

            database.insert("todos", null, values);
        }
    }

}
