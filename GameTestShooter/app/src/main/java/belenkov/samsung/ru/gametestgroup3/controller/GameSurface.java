package belenkov.samsung.ru.gametestgroup3.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import belenkov.samsung.ru.gametestgroup3.R;
import belenkov.samsung.ru.gametestgroup3.model.Bullet;
import belenkov.samsung.ru.gametestgroup3.model.Character;
import belenkov.samsung.ru.gametestgroup3.model.Enemy;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    Character character;
    ArrayList<Bullet> bulletsList;
    ArrayList<Enemy> enemiesList;

    Bitmap charBitmap;
    Bitmap bulletImage;
    Bitmap enemyBitmap;

    public GameSurface(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        character.draw(canvas);

        Iterator<Enemy> enemyIterator = enemiesList.iterator();
        Iterator<Bullet> bulletIterator = bulletsList.iterator();
        while (enemyIterator.hasNext()) {
            enemyIterator.next().draw(canvas);
        }

        while (bulletIterator.hasNext()) {
            bulletIterator.next().draw(canvas);
        }

//        for (Enemy e : enemiesList) {
//            e.draw(canvas);
//        }

//        for (Bullet b : bulletsList) {
//            b.draw(canvas);
//        }
    }

    public void update(float deltaTime) {
        character.update(deltaTime);

        for (Bullet b : bulletsList) {
            b.update(deltaTime);
            if (b.getX() < 0 || b.getY() < 0 || b.getX() > getWidth() || b.getY() > getHeight()) {
                bulletsList.remove(b);
            }
        }

        for (Enemy e : enemiesList) {
            e.setMovingVectors(character.getX() - e.getX(), character.getY() - e.getY());
            e.update(deltaTime);
            if (e.getMovingVectorLength() > 20) {
                e.rotate(character.getX(), character.getY(), 0);
            } else {
                enemiesList.remove(e);
            }
        }

        for (Bullet b : bulletsList) {
            for (Enemy e : enemiesList) {
                if (b.getX() >= e.getX() && b.getX() <= e.getX() + e.getWidth() &&
                        b.getY() >= e.getY() && b.getY() <= e.getY() + e.getHeigth()) {
                    bulletsList.remove(b);
                    enemiesList.remove(e);
                } else {
                    System.out.println("miss");
                }
            }
        }

    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();

        float x = event.getX();
        float y = event.getY();
        int movingVectorX = (int) x - character.getX();
        int movingVectorY = (int) y - character.getY();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
                character.setMovingVectors(movingVectorX, movingVectorY);
                character.rotate(x, y, 90);
                character.setRunning(true);
            case MotionEvent.ACTION_POINTER_DOWN: {
                if (event.getActionIndex() >= 1) {
                    System.out.println("second touch");
                    character.rotate(x, y, 90);
                    Bullet bullet = new Bullet(character.getX(), character.getY(), bulletImage, this);
                    bullet.setMovingVectors(movingVectorX, movingVectorY);
                    bullet.setRunning(true);
                    bulletsList.add(bullet);
                }
                System.out.println("\npointerIndex: " + pointerIndex + "\npointerId:" + pointerId + "\nmaskedAction:" + maskedAction);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                character.rotate(x, y, 90);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                character.setRunning(false);
                break;
            }
        }

        return true;
    }

    private Runnable enemySpawner = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Random random = new Random();
                Enemy enemy = new Enemy(random.nextInt(getWidth()),
                        random.nextInt(getHeight()),
                        enemyBitmap,
                        GameSurface.this);
                enemy.setRunning(true);
                enemy.rotate(character.getX(), character.getY(), 180);
                enemiesList.add(enemy);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        charBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        bulletImage = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
        enemyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        character = new Character(this, 100, 200, charBitmap);
        bulletsList = new ArrayList<>();
        enemiesList = new ArrayList<>();

        gameThread = new GameThread(this, getHolder());
        gameThread.setRunning(true);
        gameThread.start();

        Thread enemiesSpawnerThread = new Thread(enemySpawner);
        enemiesSpawnerThread.start();
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
