package belenkov.samsung.ru.sensorsgroup3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor geomagniticSensor;

    private float[] rotationMatrix;
    private float[] accelerometerData;
    private float[] geomagniticData;
    private float[] orientationData;

    private TextView xyAngle;
    private TextView xzAngle;
    private TextView zyAngle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Шаг 1 - получить сервис, который управляет сенсорами
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelerometerData = new float[3];
        geomagniticData = new float[3];
        orientationData = new float[3];

        xyAngle = findViewById(R.id.xyAngle);
        xzAngle = findViewById(R.id.xzAngle);
        zyAngle = findViewById(R.id.zyAngle);
    }

    //Начать пользоваться сенсорами
    @Override
    protected void onResume() {
        super.onResume();
        //Шаг 2 - регистрация конкретного сенсора
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        geomagniticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (accelerometerSensor != null && geomagniticSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this, geomagniticSensor, SensorManager.SENSOR_DELAY_UI);
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
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            accelerometerData = sensorEvent.values.clone();
        }
        if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagniticData = sensorEvent.values.clone();
        }

        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerData, geomagniticData);
        SensorManager.getOrientation(rotationMatrix, orientationData);

        xyAngle.setText("xy:" + String.valueOf(Math.round(Math.toDegrees(orientationData[0]))));
        xzAngle.setText("xz:" + String.valueOf(Math.round(Math.toDegrees(orientationData[1]))));
        zyAngle.setText("zy:" + String.valueOf(Math.round(Math.toDegrees(orientationData[2]))));
    }

    //Возвращает данные, когда меняется точность датчика
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
