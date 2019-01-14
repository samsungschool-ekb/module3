package belenkov.samsung.ru.adaptersmodule3.listviewdemo.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import belenkov.samsung.ru.adaptersmodule3.R;
import belenkov.samsung.ru.adaptersmodule3.listviewdemo.Car;

public class RecyclerDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);

        RecyclerView carsRecycler = findViewById(R.id.carsRecycler);

        ArrayList<Car> carsList = new ArrayList<>();
        carsList.add(new Car("Toyota", "Camry", R.drawable.car1));
        carsList.add(new Car("Nissan", "Camry", R.drawable.car2));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car3));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car4));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car5));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car1));
        carsList.add(new Car("Nissan", "Camry", R.drawable.car2));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car3));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car4));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car5));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car1));
        carsList.add(new Car("Nissan", "Camry", R.drawable.car2));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car3));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car4));
        carsList.add(new Car("Toyota", "Camry", R.drawable.car5));
        carsRecycler.setLayoutManager(new GridLayoutManager(this, 5));
        carsRecycler.setAdapter(new CarsRecycleAdapter(carsList));

    }
}
