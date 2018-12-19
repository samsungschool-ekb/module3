package belenkov.samsung.ru.gametestgroup3.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import belenkov.samsung.ru.gametestgroup3.R;
import belenkov.samsung.ru.gametestgroup3.model.Character;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;
    Character character;

    public GameSurface(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        character.draw(canvas);
    }

    public void update() {
        character.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                int x = (int)event.getX();
                int y = (int)event.getY();

                int movingVectorX = x - character.getX();
                int movingVectorY = y - character.getY();

                character.setMovingVectors(movingVectorX, movingVectorY);
                return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap charBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        character = new Character(this, 100, 200, charBitmap);
        gameThread = new GameThread(this, getHolder());
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                gameThread.setRunning(false);
            }
        }
    }
}
