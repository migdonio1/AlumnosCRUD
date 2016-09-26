package computomovil.alumnoscrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 9/22/16.
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private UserDBHelper userDBHelper;
    private String[] allColumns = {
            UserContract.COLUMN_NAME_USERNAME,
            UserContract.COLUMN_NAME_PASSWORD
    };

    public UserDataSource(Context ct) {
        userDBHelper = new UserDBHelper(ct);
    }

    public void open() throws SQLException {
        database = userDBHelper.getWritableDatabase();
    }

    public void close() {
        userDBHelper.close();
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        User user;

        return users;
    }

    /**
     * Function oriented to insert a new User into DataBase
     * @param username
     * @param password
     * @return row ID of the newly User inserted row, or -1
     */
    public long insertUser(String username, String password){
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_NAME_USERNAME, username);
        values.put(UserContract.COLUMN_NAME_PASSWORD, password);

        long newRowId;
        newRowId = database.insert(UserContract.TABLE_NAME,null,values);
        return newRowId;
    }

    public boolean validUser(String username, String passwordWritten)
    {
        Cursor cursor=database.query(UserContract.TABLE_NAME, null, " username=?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return false;
        }

        cursor.moveToFirst();
        String passwordUser = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();

        if(!passwordWritten.equals(passwordUser)){
            return false;
        }

        return true;
    }
}
