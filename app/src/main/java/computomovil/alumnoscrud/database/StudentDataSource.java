package computomovil.alumnoscrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import computomovil.alumnoscrud.models.Student;

/**
 * Created by root on 9/19/16.
 */
public class StudentDataSource {
    private SQLiteDatabase database;
    private StudentDBHelper studentDBHelper;
    private String[] allColumns = {
            StudentContract._ID,
            StudentContract.COLUMN_NAME_FIRSTNAME,
            StudentContract.COLUMN_NAME_LASTNAME,
            StudentContract.COLUMN_NAME_DEGREE
    };

    public StudentDataSource(Context context){
        studentDBHelper = new StudentDBHelper(context);
    }

    public void open() throws SQLException {
        database = studentDBHelper.getWritableDatabase();
    }

    public void close(){
        studentDBHelper.close();
    }

    /**
     * Function oriented to insert a new Student into DataBase
     * @param firstname
     * @param lastname
     * @param degree
     * @return row ID of the newly Student inserted row, or -1
     */
    public long insertStudent(String firstname, String lastname, String degree){
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NAME_FIRSTNAME, firstname);
        values.put(StudentContract.COLUMN_NAME_LASTNAME, lastname);
        values.put(StudentContract.COLUMN_NAME_DEGREE, degree);
        
        long newRowId;
        newRowId = database.insert(StudentContract.TABLE_NAME,null,values);
        return newRowId;
    }

    public boolean removeStudentById(int id){
        return database.delete(StudentContract.TABLE_NAME, StudentContract._ID + "=" + id, null)>0;
    }

    public void updateStudentById(int id, String firstname, String lastname, String degree) {
        ContentValues values = new ContentValues();

        values.put(StudentContract.COLUMN_NAME_FIRSTNAME, firstname);
        values.put(StudentContract.COLUMN_NAME_LASTNAME, lastname);
        values.put(StudentContract.COLUMN_NAME_DEGREE, degree);

        int result = database.update(StudentContract.TABLE_NAME, values, StudentContract._ID + "=" + id, null);
        Log.d("TEST DB3", String.valueOf(result));
    }

    /**
     * Function oriented to recovery all Student's rows from Database
     * @return List of Alumnos from Database
     */

    public List<String> getAllStudentsNames() {
        List<String> studentsNames = new ArrayList<>();
        Student student;

        Cursor cursor = database.query(StudentContract.TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            student = cursorToStudent(cursor);
            studentsNames.add(student.getFirstname());
            cursor.moveToNext();
        }
        cursor.close();

        return studentsNames;
    }

    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<Student>();
        Student student;
        Cursor cursor = database.query(StudentContract.TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            student = cursorToStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    private Student cursorToStudent(Cursor cursor){
        Student student = new Student();
        student.setId(cursor.getInt(0));
        student.setFirstname(cursor.getString(1));
        student.setLastname(cursor.getString(2));
        student.setDegree(cursor.getString(3));
        return student;
    }

}

