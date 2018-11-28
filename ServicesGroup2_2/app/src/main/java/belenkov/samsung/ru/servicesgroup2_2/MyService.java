package belenkov.samsung.ru.servicesgroup2_2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    MyTask myTask;
    public static final String SERVICE_NAME = "WEATHER_SERVICE";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myTask = new MyTask();
        myTask.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Сервис остановлен");
        myTask.cancel(true);
    }

    class MyTask extends AsyncTask<Void, Void, ArrayList<Weather>> {

        @Override
        protected void onPostExecute(ArrayList<Weather> weathersList) {
            super.onPostExecute(weathersList);
            try {
                for (Weather w : weathersList) {
                    showNotification(String.format("Погодные данные: дата: %s, температура: %s, влажность:%s",
                            w.getDate(), w.getTemp(), w.getHumidity()));
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException e) {
                Toast.makeText(MyService.this, "something wrong", Toast.LENGTH_SHORT).show();
            }
            /**Вот так связывать сервис с активностью
             Метод SendBroadcast выдает в систему интент
             А в активности его слушает BroadcastReceiver
             Надо раскоментировать чтобы заработало**/
//            Intent intent = new Intent(SERVICE_NAME);
//            intent.putExtra("weather", weathersList.get(0));
//            sendBroadcast(intent);
        }

        void showNotification(String msg) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, "weather_notify");
            builder
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_cloud)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle("Информация по погоде")
                    .setContentText(msg);
            NotificationManager notificationManager = (NotificationManager) MyService.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, builder.build());
        }

        @Override
        protected ArrayList<Weather> doInBackground(Void... voids) {
            ArrayList<Weather> weathersList = new ArrayList<>();
            String result;

            try {
                URL url = new URL("http://icomms.ru/inf/meteo.php?tid=18");
                Scanner in = new Scanner((InputStream) url.getContent());
                result = "{\"weather\":" + in.nextLine() + "}";
                JSONObject weatherObject = new JSONObject(result);
                JSONArray weatherData = weatherObject.getJSONArray("weather");
                for (int i = 0; i < weatherData.length(); i++) {
                    JSONObject currentWeatherInfo = weatherData.getJSONObject(i);

                    String date = currentWeatherInfo.getString("date");
                    String temp = currentWeatherInfo.getString("temp");
                    String humidity = currentWeatherInfo.getString("humidity");

                    Weather weather = new Weather(date, temp, humidity);
                    weathersList.add(weather);
                }

            } catch (MalformedURLException e) {
                System.out.println("Плохая веб-страничка");
            } catch (IOException e) {
                System.out.println("Неверный формат данных");
            } catch (JSONException e) {
                System.out.println("Не могу получить JSON");
            }
            return weathersList;
        }
    }
}
