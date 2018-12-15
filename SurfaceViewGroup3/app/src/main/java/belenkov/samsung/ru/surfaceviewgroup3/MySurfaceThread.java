package belenkov.samsung.ru.surfaceviewgroup3;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private MySurfaceView surfaceView;
    private boolean drawing;

    public MySurfaceThread(SurfaceHolder holder,
                           MySurfaceView view) {
        this.surfaceHolder = holder;
        this.surfaceView = view;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        Canvas canvas = null;
        while (drawing) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceView) {
                    surfaceView.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
