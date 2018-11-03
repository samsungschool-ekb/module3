package schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.R;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.adapters.TeachersRecyclerAdapter;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Teacher;

public class TeachersItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_items);

        Teacher[]teachersList = (Teacher[])getIntent().getSerializableExtra("teachersList");

        RecyclerView teacherRecycler = findViewById(R.id.teacherRecycler);
        teacherRecycler.setAdapter(new TeachersRecyclerAdapter(teachersList, this));
        teacherRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
