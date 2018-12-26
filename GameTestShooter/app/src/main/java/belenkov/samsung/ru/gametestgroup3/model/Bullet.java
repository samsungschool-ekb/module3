package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;

import belenkov.samsung.ru.gametestgroup3.controller.GameSurface;

public class Bullet extends BaseGameObject {
    public Bullet(int x, int y, Bitmap image, GameSurface gameSurface) {
        super(x, y, 1, 1, image, gameSurface);
        velocity = 3;
    }
}
