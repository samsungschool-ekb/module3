package schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.R;

public class TeacherDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        String name = getIntent().getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }
}
