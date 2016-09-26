package computomovil.alumnoscrud.database;

import android.provider.BaseColumns;

/**
 * Created by root on 9/19/16.
 */
public class StudentContract implements BaseColumns {
    public static final String TABLE_NAME = "Students";
    public static final String COLUMN_NAME_FIRSTNAME = "firstname";
    public static final String COLUMN_NAME_LASTNAME = "lastname";
    public static final String COLUMN_NAME_DEGREE = "degree";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_STUDENTS =
            "CREATE TABLE " + TABLE_NAME + " (" +_ID + " INTEGER PRIMARY KEY,"+
                    COLUMN_NAME_FIRSTNAME+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_LASTNAME+TEXT_TYPE+COMMA_SEP+
                    COLUMN_NAME_DEGREE+
                    " )";

    public static final String SQL_DELETE_STUDENTS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
