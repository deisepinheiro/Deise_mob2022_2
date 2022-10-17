package br.edu.uniritter.mobile.mobile20222_1.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.mobile20222_1.model.User;

public class UserSQLRepository {
    private final String TAG = "UserSQLRepository";
    private List<User> users;
    private static UserSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public static UserSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserSQLRepository(contexto);
        }
        return instance;
    }

    public List<User> getAllUsers(){
        users = new ArrayList<>();
        String sql = "select "+
                DataBaseHelper.USER_ID +" , "+
                DataBaseHelper.USER_NAME+" , "+
                DataBaseHelper.USER_LOGIN+" , "+
                DataBaseHelper.USER_PASSWORD +
                " from " + DataBaseHelper.TABLE_USERS ;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor != null && cursor.moveToFirst()) {
            do{
                User user = new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                users.add(user);
            }while (cursor.moveToNext());
        }

        return users;
    }

    public void saveUsers(List<User> users){
        //for (int i = 0; i < users.size(); i++) {
        for (User u : users) {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.USER_ID, u.getId() );
            values.put(DataBaseHelper.USER_NAME, u.getName() );
            values.put(DataBaseHelper.USER_LOGIN, u.getUserLogin() );
            values.put(DataBaseHelper.USER_PASSWORD, u.getPassword() );
            // Insert the new row
            database.insert(DataBaseHelper.TABLE_USERS, null, values);
        }
    }

    private UserSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }
}
