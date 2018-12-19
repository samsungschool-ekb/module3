package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import belenkov.samsung.ru.gametestgroup3.controller.GameSurface;

public class Character extends BaseGameObject {

    private final int ROW_TOP_TO_BOTTOM = 0;
    private final int ROW_RIGHT_TO_LEFT = 1;
    private final int ROW_LEFT_TO_RIGHT = 2;
    private final int ROW_BOTTOM_TO_TOP = 3;
    private final float VELOCITY = 0.1f;

    private int rowUsing = ROW_RIGHT_TO_LEFT;
    private int colUsing;

    private Bitmap[] topToBottoms;
    private Bitmap[] rightToLefts;
    private Bitmap[] leftToRights;
    private Bitmap[] bottomToTops;

    private int movingVectorX = 10;
    private int movingVectorY = 5;

    private GameSurface gameSurface;

    private float lastUpdateTime;

    public Character(GameSurface gameSurface, int x, int y, Bitmap image) {
        super(x, y, image, 4, 3);
        this.gameSurface = gameSurface;
        topToBottoms = new Bitmap[colCount];
        rightToLefts = new Bitmap[colCount];
        leftToRights = new Bitmap[colCount];
        bottomToTops = new Bitmap[colCount];

        for (int i = 0; i < colCount; i++) {
            topToBottoms[i] = createSubimage(ROW_TOP_TO_BOTTOM, i);
            rightToLefts[i] = createSubimage(ROW_RIGHT_TO_LEFT, i);
            leftToRights[i] = createSubimage(ROW_LEFT_TO_RIGHT, i);
            bottomToTops[i] = createSubimage(ROW_BOTTOM_TO_TOP, i);
        }
    }


    public void update() {
        colUsing++;
        if (colUsing >= colCount) {
            colUsing = 0;
        }

        long now = System.nanoTime();

        if (lastUpdateTime == -1) {
            lastUpdateTime = now;
        }

        int deltaTime = (int) ((now - lastUpdateTime) / 1000000);

        float distace = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(
                movingVectorX * movingVectorX +
                        movingVectorY * movingVectorY);

        this.x = x + (int) (distace * movingVectorX / movingVectorLength);
        this.y = y + (int) (distace * movingVectorY / movingVectorLength);

        //Ограничение движения
        if (x < 0) {
            x = 0;
            movingVectorX = -movingVectorX;
        } else if (x > gameSurface.getWidth() - getWidth()) {
            x = gameSurface.getWidth() - getWidth();
            movingVectorX = -movingVectorX;
        }

        if (y < 0) {
            y = 0;
            movingVectorY = -movingVectorY;
        } else if (y > gameSurface.getHeight() - getHeigth()) {
            y = gameSurface.getHeight() - getHeigth();
            movingVectorY = -movingVectorY;
        }

        if (movingVectorX > 0) {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
                rowUsing = ROW_LEFT_TO_RIGHT;
            }
        } else {
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
                rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }

    public void setMovingVectors(int moveX, int moveY){
        movingVectorX = moveX;
        movingVectorY = moveY;
    }

    private Bitmap[] getColBitmap() {
        switch (rowUsing) {
            case ROW_BOTTOM_TO_TOP:
                return bottomToTops;
            case ROW_TOP_TO_BOTTOM:
                return topToBottoms;
            case ROW_LEFT_TO_RIGHT:
                return leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return rightToLefts;
            default:
                return null;
        }
    }

    private Bitmap getCurrentMoveBitmap() {
        return getColBitmap() != null ? getColBitmap()[colUsing] : null;
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        lastUpdateTime = System.nanoTime();
    }
}
