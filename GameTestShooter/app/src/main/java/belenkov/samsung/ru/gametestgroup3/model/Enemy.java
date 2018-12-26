package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;

import belenkov.samsung.ru.gametestgroup3.controller.GameSurface;

public class Enemy extends BaseGameObject {
    public Enemy(int x, int y, Bitmap image, GameSurface gameSurface) {
        super(x, y, 3, 9, image, gameSurface);
        velocity = 0.5f;
    }
}
