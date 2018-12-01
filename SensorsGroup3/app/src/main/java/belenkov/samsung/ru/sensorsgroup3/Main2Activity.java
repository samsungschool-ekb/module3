package belenkov.samsung.ru.sensorsgroup3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {

    TextView proximityTextView;
    TextView acellTextView;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private Sensor acellSensor;

    private long lastShakeTime;
    private long shakeIntervalTime = 1000;
    private double shakeTrashHold = 3.25f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximityTextView = findViewById(R.id.proximityTextView);
        acellTextView = findViewById(R.id.accelTextView);
    }

    //Начать пользоваться сенсорами
    @Override
    protected void onResume() {
        super.onResume();
        //Шаг 2 - регистрация конкретного сенсора
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        acellSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if (proximitySensor != null && acellSensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this, acellSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    //Перестать пользоваться сенсорами
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //Возрвращает данные от сенсора
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final int sensorType = sensorEvent.sensor.getType();
        if (sensorType == Sensor.TYPE_PROXIMITY) {
            if (sensorEvent.values[0] >= -10 && sensorEvent.values[0] <= 10) {
                proximityTextView.setText("Рука близко");
            } else {
                proximityTextView.setText("Рука далеко");
            }
        }
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastShakeTime) > shakeIntervalTime) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2)
                        + Math.pow(y, 2)
                        + Math.pow(z, 2))
                        - SensorManager.GRAVITY_EARTH;

                acellTextView.setText(String.valueOf(Math.round(acceleration)));
                if (acceleration > shakeTrashHold) {
                    lastShakeTime = currentTime;
                    Toast.makeText(this, "SHAKE SHAKE", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //Возвращает данные, когда меняется точность датчика
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
