package computomovil.alumnoscrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 9/19/16.
 */
public class StudentsListActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);

        lv = (ListView) findViewById(R.id.listView);

        final StudentDataSource studentsDS = new StudentDataSource(getApplicationContext());
        studentsDS.open();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                List<Student> students = studentsDS.getAllStudents();
                Student studentSelected = students.get(i);


                Intent intent = new Intent(getApplicationContext(), ViewStudentActivity.class);
                intent.putExtra("firstname", studentSelected.getFirstname());
                intent.putExtra("lastname", studentSelected.getLastname());
                intent.putExtra("degree", studentSelected.getDegree());
                intent.putExtra("id", studentSelected.getId());
                startActivity(intent);
            }
        });

        List<String> studentsNames = studentsDS.getAllStudentsNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                studentsNames
        );

        lv.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onClickAddButton(View v) {
        Intent intent = new Intent(this, CreateStudentActivity.class);
        startActivity(intent);
    }
}
