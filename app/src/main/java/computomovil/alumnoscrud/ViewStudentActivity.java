package computomovil.alumnoscrud;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    private String firstname;
    private String lastname;
    private String degree;
    private int id;

    private TextView firstnameTexView;
    private TextView lastnameTexView;
    private TextView degreeTexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        Intent intent = getIntent();
        setContentStudent(intent);

    }


    public void onClickEditButton(View v) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        intent.putExtra("firstname",firstname);
        intent.putExtra("lastname",lastname);
        intent.putExtra("degree",degree);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void onClickDeleteButton(View v) {
        Intent intent = new Intent(this, StudentsListActivity.class);

        final StudentDataSource studentsDS = new StudentDataSource(this);
        studentsDS.open();
        studentsDS.removeStudentById(id);
        studentsDS.close();

        startActivity(intent);
    }

    private void setContentStudent(Intent intent) {
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        degree = intent.getStringExtra("degree");
        id = intent.getIntExtra("id",0);

        firstnameTexView = (TextView)findViewById(R.id.firstname);
        lastnameTexView = (TextView)findViewById(R.id.lastname);
        degreeTexView = (TextView)findViewById(R.id.degree);

        firstnameTexView.setText(firstname);
        lastnameTexView.setText(lastname);
        degreeTexView.setText(degree);
    }
}
