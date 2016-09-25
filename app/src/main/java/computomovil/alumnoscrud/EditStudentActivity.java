package computomovil.alumnoscrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    private String firstname;
    private String lastname;
    private String degree;

    private EditText editTextFirstname;
    private EditText editTextLastname;
    private Spinner spinnerDegree;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Intent intent = getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        degree = intent.getStringExtra("degree");
        id = intent.getIntExtra("id",0);


        editTextFirstname  = (EditText)findViewById(R.id.firstnameEditText);
        editTextLastname   = (EditText)findViewById(R.id.lastnameEditText);
        spinnerDegree       = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.degree, android.R.layout.simple_spinner_dropdown_item);

        editTextFirstname.setText(firstname);
        editTextLastname.setText(lastname);
        spinnerDegree.setAdapter(adapter);
        spinnerDegree.setSelection(selectDegree(degree));
    }

    public void onClickUpdateButton(View view){
        Intent intent = new Intent(this, StudentsListActivity.class);

        final StudentDataSource studentsDS = new StudentDataSource(this);
        studentsDS.open();

        Log.d("TEST AIUDAAAAA", editTextFirstname.getText().toString().trim());
        Log.d("TEST AIUDAAAAA", editTextLastname.getText().toString().trim());

        studentsDS.updateStudentById(id,
                editTextFirstname.getText().toString().trim(),
                editTextLastname.getText().toString().trim(),
                spinnerDegree.getSelectedItem().toString()
        );
        studentsDS.close();
        startActivity(intent);
    }

    public void onClickBackButton(View view) {
        finish();
    }

    private int selectDegree(String degree) {
        switch (degree){
            case "LIS":
                return 0;
            case "LCC":
                return 1;
            case "LIC":
                return 2;
            case "LEM":
                return 3;
            case "LA":
                return 4;
            case "LM":
                return 5;
        }
        return 0;
    }
}
