package belenkov.samsung.ru.adaptersmodule3.listviewdemo;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import belenkov.samsung.ru.adaptersmodule3.R;

public class GridViewDemo extends AppCompatActivity {

    private Car[] cars;

    private GridView carsGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_demo);
        cars = new Car[]{
//                new Car("Toyota", "Camry"),
//                new Car("Nissan", "skyline"),
//                new Car("Porshe", "911"),
//                new Car("BMW", "x3"),
//                new Car("Hammer", "h2"),
        };

        carsGrid = findViewById(R.id.carsGrid);

        ArrayAdapter<Car> cheeseAdapter =
                new ArrayAdapter<Car>(
                        this,
                        0,
                        cars) {
                    @SuppressLint("InflateParams")
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        Car currentCheese = cars[position];
                        if (convertView == null) {
                            convertView = getLayoutInflater()
                                    .inflate(R.layout.car_2_item, null, false);
                            TextView cheeseName = convertView.findViewById(R.id.carName);
                            TextView cheeseDescription = convertView.findViewById(R.id.carDescription);

                            cheeseName.setText(currentCheese.getName());
                            cheeseDescription.setText(currentCheese.getDescription());
                        }
                        return convertView;
                    }
                };

        carsGrid.setAdapter(cheeseAdapter);
    }
}
