package computomovil.alumnoscrud.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import computomovil.alumnoscrud.R;
import computomovil.alumnoscrud.models.User;
import computomovil.alumnoscrud.database.UserDataSource;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN = "login";

    public EditText inputUsername;
    public EditText inputPassword;
    public User userActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        userActive = sharedPreferencesRead();

        if(!Objects.equals(userActive.getUsername(), "none") && !Objects.equals(userActive.getPassword(), "none")){
            Intent intent = new Intent(this, StudentsListActivity.class);
            startActivity(intent);
        }

/*
        UserDataSource userDS = new UserDataSource(this);

        userDS.open();
        userDS.insertUser("migdonio", "123456");
        userDS.close();
*/


       /* StudentDataSource studentsDS = new StudentDataSource(this);
        studentsDS.open();

        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio", "Rodriguez Te", "LCC");
        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio2", "Rodriguez Te", "LIS");
        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio3", "Rodriguez Te", "LCC");
        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio4", "Rodriguez Te", "LCC");
        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio5", "Rodriguez Te", "LIC");
        studentsDS.insertStudent("migdonio1","123456", "Raul Migdonio6", "Rodriguez Te", "LCC");

        List<Student> student = studentsDS.getAllStudents();

        studentsDS.close();

        Log.d("TEST", String.valueOf(student.size()));
        Log.d("TEST", student.get(0).getFirstname());*/
    }

    public void onClickLoginButton(View v) {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString();

        UserDataSource userDS = new UserDataSource(this);

        userDS.open();
        boolean validUser = userDS.validUser(username,password);
        userDS.close();

        if(!validUser) {
            Toast loginFailedMessage = Toast.makeText(
                    getApplicationContext(),
                    "Usuario o contraseña invalido",
                    Toast.LENGTH_SHORT);
            loginFailedMessage.show();
        } else {
            Intent intent = new Intent(this, StudentsListActivity.class);
            sharedPreferencesUpdate(username, password);
            startActivity(intent);
        }
    }

    private void sharedPreferencesUpdate(String username, String password) {
        SharedPreferences settings = getSharedPreferences(LOGIN, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("username", username);
        editor.putString("password", password);

        editor.commit();
    }

    private User sharedPreferencesRead(){
        User userSaved = new User();
        SharedPreferences settings = getSharedPreferences(LOGIN,0); //0 means private mode
        Log.d("login",settings.getString("username", "none"));
        Log.d("login",settings.getString("password", "none"));
        userSaved.setUsername(settings.getString("username", "none"));
        userSaved.setPassword(settings.getString("password", "none"));
        return userSaved;
    }
}
