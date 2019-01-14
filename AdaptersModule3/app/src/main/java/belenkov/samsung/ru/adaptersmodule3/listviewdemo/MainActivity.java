package belenkov.samsung.ru.adaptersmodule3.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import belenkov.samsung.ru.adaptersmodule3.R;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private String[] cars;
    private ArrayAdapter<String> carsAdapter;

    private ListView carsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cars = new String[]{"Toyota", "Nissan", "VAZ", "BMW", "Porshe",
                "Toyota", "Nissan", "VAZ", "BMW", "Porshe",
                "Toyota", "Nissan", "VAZ", "BMW", "Porshe",
                "Toyota", "Nissan", "VAZ", "BMW", "Porshe"};

        carsAdapter = new ArrayAdapter<String>(this, R.layout.car_item, R.id.carName, cars);

        carsListView = findViewById(R.id.carsListView);
        carsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "clicked" + cars[position], Toast.LENGTH_SHORT).show();
            }
        });
        carsListView.setAdapter(carsAdapter);
    }
}
