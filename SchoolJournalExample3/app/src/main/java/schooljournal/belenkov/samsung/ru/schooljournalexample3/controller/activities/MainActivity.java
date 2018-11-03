package schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.R;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.School;

public class MainActivity extends AppCompatActivity {

    School school = new School();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        school.setName("Best school in the world");
        school.setAddress("Baker Street 221B");

        TextView schoolName = findViewById(R.id.schoolName);
        TextView schoolAddress = findViewById(R.id.schoolAddress);
        TextView schoolTeachers = findViewById(R.id.schoolTeachers);

        schoolName.setText("name: " + school.getName());
        schoolAddress.setText("address: " + school.getAddress());
        schoolTeachers.setText("teachers: " + school.getTeachersList().length);
    }

    public void goToTeachersItems(View view) {
        Intent intent = new Intent(this, TeachersItemsActivity.class);
        intent.putExtra("teachersList", school.getTeachersList());
        startActivity(intent);
    }
}
