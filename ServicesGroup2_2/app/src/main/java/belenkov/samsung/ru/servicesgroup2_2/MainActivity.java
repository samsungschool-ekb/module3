package belenkov.samsung.ru.servicesgroup2_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Weather weather = (Weather) intent.getSerializableExtra("weather");
            Toast.makeText(MainActivity.this, weather.getTemp(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /**Надо раскоментировать чтобы заработало**/
//        registerReceiver(receiver, new IntentFilter(MyService.SERVICE_NAME));
    }

    public void goToActivity2(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("stroke", "stroke from first activity");
        intent.putExtra("weatherObj", new Weather("27-11-18", "-10", "20%"));
        startActivity(intent);
    }

    public void startService(View view) {
        startService(new Intent(this, MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
    }
}
