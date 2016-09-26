package computomovil.alumnoscrud.database;

import android.provider.BaseColumns;

/**
 * Created by root on 9/22/16.
 */
public class UserContract implements BaseColumns {
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_PASSWORD = "password";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_USERS =
            "CREATE TABLE " + TABLE_NAME + " (" +_ID + " INTEGER PRIMARY KEY,"+
                    COLUMN_NAME_USERNAME+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_PASSWORD+TEXT_TYPE+
                    " )";

    public static final String SQL_DELETE_USERS=
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
