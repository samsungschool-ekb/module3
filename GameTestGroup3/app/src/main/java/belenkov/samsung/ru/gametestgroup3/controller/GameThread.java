package belenkov.samsung.ru.gametestgroup3.controller;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private boolean running;
    private GameSurface surface;
    private SurfaceHolder surfaceHolder;

    GameThread(GameSurface surface, SurfaceHolder surfaceHolder) {
        this.surface = surface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;

            canvas = surfaceHolder.lockCanvas();
            synchronized (canvas) {
                try {
                    surface.update();
                    surface.draw(canvas);
                    sleep(24);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
