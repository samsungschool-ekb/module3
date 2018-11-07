package io.tutorial.belenkov.samsung.ru.iotutorial3;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamDemo {
    private Context context;
    private File file;

    public BufferedStreamDemo(Context context) {
        this.context = context;
        this.file = new File(context.getExternalFilesDir("fileStreamDemo"), "fileStream.txt");
    }


    public void write(String data) throws IOException {
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buffer = data.getBytes();
        fos.write(buffer);
        fos.close();
        Toast.makeText(context, "write succesful", Toast.LENGTH_SHORT).show();
    }

    public void read() throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        StringBuilder builder = new StringBuilder();
        byte[]buffer = new byte[1024];
        int fileByte;
        while ((fileByte = fis.read(buffer)) != -1) {
            builder.append(fileByte);
        }
        Toast.makeText(context, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}
