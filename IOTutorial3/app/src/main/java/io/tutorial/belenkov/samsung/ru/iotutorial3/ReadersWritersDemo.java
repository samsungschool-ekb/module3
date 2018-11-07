package io.tutorial.belenkov.samsung.ru.iotutorial3;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadersWritersDemo {
    private Context context;
    private File file;

    public ReadersWritersDemo(Context context) {
        this.context = context;
        this.file = new File(context.getExternalFilesDir("fileStreamDemo"), "writersDemo.txt");
    }

    public void write(String data) throws IOException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(data);
        printWriter.flush();
    }

    public void read() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            builder.append(line);
        }
        Toast.makeText(context, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}
