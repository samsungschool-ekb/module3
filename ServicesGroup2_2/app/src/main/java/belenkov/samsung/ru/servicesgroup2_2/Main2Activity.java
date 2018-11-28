package belenkov.samsung.ru.servicesgroup2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String strokeFromFirstActivity = getIntent().getStringExtra("stroke");
        Weather weatherFromFirstActivity = (Weather) getIntent().getSerializableExtra("weatherObj");

        Toast.makeText(this, String.format("Строка из первой активности: %s%n" +
                        "Погодные данные: дата: %s, температура: %s, влажность:%s", strokeFromFirstActivity,
                weatherFromFirstActivity.getDate(), weatherFromFirstActivity.getTemp(), weatherFromFirstActivity.getHumidity())
                , Toast.LENGTH_LONG).show();
    }
}
