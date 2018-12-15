package belenkov.samsung.ru.surfaceviewgroup3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView
        implements SurfaceHolder.Callback {

    Paint paint;
    boolean draw = true;
    float pointX, pointY;
    float fingerX, fingerY;

    MySurfaceThread surfaceThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        pointX = fingerX = 0;
        pointY = fingerY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (draw) {
            canvas.drawRGB(0, 0, 0);
            canvas.drawCircle(pointX, pointY, 25, paint);
            if ((pointX == fingerX) && (pointY == fingerY)) {
                draw = false;
            } else {
                pointX = (pointX + fingerX) / 2;
                pointY = (pointY + fingerY) / 2;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                fingerX = event.getX();
                fingerY = event.getY();
                draw = true;
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceThread = new MySurfaceThread(getHolder(), this);
        surfaceThread.setDrawing(true);
        surfaceThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        surfaceThread.setDrawing(false);
        while (retry){
            try {
                surfaceThread.join();
                retry = false;
            } catch (InterruptedException e) { }
        }
    }
}
