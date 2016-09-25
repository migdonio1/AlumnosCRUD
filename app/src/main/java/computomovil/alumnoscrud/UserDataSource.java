package computomovil.alumnoscrud;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        Cursor cursor =
    }


}
