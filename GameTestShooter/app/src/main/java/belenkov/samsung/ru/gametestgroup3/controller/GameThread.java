package belenkov.samsung.ru.gametestgroup3.controller;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private boolean running;
    private GameSurface surface;
    private SurfaceHolder surfaceHolder;

    long deltaTime;

    GameThread(GameSurface surface, SurfaceHolder surfaceHolder) {
        this.surface = surface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        long tickTime = System.currentTimeMillis();
        while (running) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (canvas) {
                    surface.update(deltaTime);
                    surface.draw(canvas);
                }
            } catch (Exception e) { } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            deltaTime = System.currentTimeMillis() - tickTime;
                try {
                    Thread.sleep(24);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            tickTime = System.currentTimeMillis();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
