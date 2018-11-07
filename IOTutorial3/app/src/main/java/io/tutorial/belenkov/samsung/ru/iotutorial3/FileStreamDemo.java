package io.tutorial.belenkov.samsung.ru.iotutorial3;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamDemo {

    private Context context;
    private File file;

    public FileStreamDemo(Context context) {
        this.context = context;
        this.file = new File(context.getExternalFilesDir("fileStreamDemo"), "fileStream.txt");
    }


    public void write(String data) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data.getBytes());
        fos.close();
        Toast.makeText(context, "write succesful", Toast.LENGTH_SHORT).show();
    }

    public void read() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        StringBuilder builder = new StringBuilder();
        int fileByte;
        while ((fileByte = fis.read()) != -1) {
            builder.append(fileByte);
        }
        Toast.makeText(context, builder.toString(), Toast.LENGTH_SHORT).show();
    }

}
