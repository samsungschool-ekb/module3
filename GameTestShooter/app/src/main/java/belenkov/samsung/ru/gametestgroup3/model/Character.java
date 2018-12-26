package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;

import belenkov.samsung.ru.gametestgroup3.controller.GameSurface;

public class Character extends BaseGameObject {


    public Character(GameSurface gameSurface, int x, int y, Bitmap image) {
        super(x, y, 1, 4, image, gameSurface);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        //Ограничение движения
        if (x < 0) {
            x = 0;
            movingVectorX = -movingVectorX;
            angle = 360;
        } else if (x > gameSurface.getWidth() - getWidth()) {
            x = gameSurface.getWidth() - getWidth();
            movingVectorX = -movingVectorX;
            angle = 180;
        }

        if (y < 0) {
            y = 0;
            movingVectorY = -movingVectorY;
            angle = 90;
        } else if (y > gameSurface.getHeight() - getHeigth()) {
            y = gameSurface.getHeight() - getHeigth();
            movingVectorY = -movingVectorY;
            angle = -90;
        }
    }
}
