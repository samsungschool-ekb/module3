package io.tutorial.belenkov.samsung.ru.iotutorial3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileDemo fileDemo = new FileDemo(this);
        TextView fileDemoTextView = findViewById(R.id.fileInfoTextView);

        fileDemoTextView.setText(fileDemo.getFileInfo());
        FileStreamDemo fileStreamDemo = new FileStreamDemo(this);
        BufferedStreamDemo bFileStreamDemo = new BufferedStreamDemo(this);
        ReadersWritersDemo readersWritersDemo = new ReadersWritersDemo(this);
        try {
//            fileStreamDemo.write("dsalhfsjkdfhsjkdfhsjkdlfhsld");
//            fileStreamDemo.read();

            readersWritersDemo.write("Hello nigga");
            readersWritersDemo.read();
            readersWritersDemo.write(((TextView)findViewById(R.id.fileInfoTextView)).getText().toString());
        } catch (IOException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
