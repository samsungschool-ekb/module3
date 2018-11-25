package belenkov.samsung.ru.asynctaskexample3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button loadButton;
    private Button cancelButton;
    private ProgressBar loadProgressBar;
    private TextView loadProgressTextView;

    private BigFileLoadTask bigFileLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadButton = findViewById(R.id.loadButton);
        cancelButton = findViewById(R.id.cancelButton);
        loadProgressBar = findViewById(R.id.loadProgressBar);
        loadProgressTextView = findViewById(R.id.loadProgressTextView);

        bigFileLoadTask = new BigFileLoadTask();

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               bigFileLoadTask.execute();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigFileLoadTask.cancel(true);
            }
        });
    }

    class BigFileLoadTask extends AsyncTask<Void, Integer, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Загрузка началась!", Toast.LENGTH_SHORT).show();
            loadButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int counter = 0;
            try {
                for (counter = 0; counter < 100; counter += 10) {
                    if(isCancelled())return null;
                    publishProgress(counter);
                    TimeUnit.SECONDS.sleep(1);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            return counter;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loadProgressTextView.setText("Загружено на " + values[0] + "%");
            loadProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer num) {
            super.onPostExecute(num);
            loadProgressTextView.setText("загружено на " + num + "%");
            loadProgressBar.setProgress(0);
            loadButton.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            loadProgressTextView.setText("загрузка прервана");
            loadProgressBar.setProgress(0);
            loadButton.setVisibility(View.VISIBLE);
        }
    }
}
