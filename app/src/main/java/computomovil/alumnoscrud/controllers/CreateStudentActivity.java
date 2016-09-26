package computomovil.alumnoscrud.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import computomovil.alumnoscrud.R;
import computomovil.alumnoscrud.database.StudentDataSource;

public class CreateStudentActivity extends AppCompatActivity {

    private String firstname;
    private String lastname;
    private String degree;

    EditText editTextFirstname;
    EditText editTextLastname;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        editTextFirstname = (EditText) findViewById(R.id.firstnameEditText);
        editTextLastname = (EditText) findViewById(R.id.lastnameEditText);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.degree, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onClickCreateButton(View view) {
        Intent intent = new Intent(this, StudentsListActivity.class);
        final StudentDataSource studentsDS = new StudentDataSource(this);
        studentsDS.open();

        studentsDS.insertStudent(
                editTextFirstname.getText().toString().trim(),
                editTextLastname.getText().toString().trim(),
                spinner.getSelectedItem().toString().trim()
        );

        startActivity(intent);
    }

    public void onClickBacMenukButton(View view) {
        finish();
    }
}
