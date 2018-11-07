package io.tutorial.belenkov.samsung.ru.iotutorial3;

import android.content.Context;
import android.widget.Toast;

import java.io.File;

public class FileDemo {

    File file;
    Context context;

    FileDemo(Context context) {
        file = new File(context.getExternalFilesDir("testFiles"), "fileExample.txt");
    }


    public String getFileInfo() {
        String fileInfo = String.format("" +
                        "filename:%s%n" +
                        "file_size:%d%n" +
                        "fileReadable:%b%n" +
                        "fileWritable:%b%n" +
                        "filePath:%s%n",
                file.getName(),
                file.length(),
                file.canRead(),
                file.canWrite(),
                file.getAbsolutePath());
        return fileInfo;
    }
}
