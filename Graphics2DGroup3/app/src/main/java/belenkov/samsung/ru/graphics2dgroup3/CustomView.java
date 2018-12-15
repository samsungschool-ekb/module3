package belenkov.samsung.ru.graphics2dgroup3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

    Path path;
    Paint paint;
    Bitmap car;

    float x, y;

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
        Resources res = getResources();
        car = BitmapFactory.decodeResource(res, R.drawable.car);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(50, 150, 255));

        canvas.drawPaint(paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        canvas.drawRect(0, 150, 150, 0, paint);

        canvas.drawCircle(200, 200, 100, paint);
        canvas.drawOval(0, 300, 50, 0, paint);

        canvas.drawBitmap(car, 200, 200, paint);

        canvas.save();
        canvas.rotate(45, 200 + (car.getWidth() / 2), 500 + (car.getHeight() / 2));
        canvas.drawBitmap(car, 200, 500, paint);

        canvas.restore();

//        canvas.drawBitmap(car, x, y, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
               path.lineTo(event.getX(), event.getRawY());
                break;
        }
        invalidate();
        return true;
    }
}
