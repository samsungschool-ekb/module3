package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import belenkov.samsung.ru.gametestgroup3.controller.GameSurface;

public class BaseGameObject {
    protected float velocity = 1f;
    protected float angle = 0;

    protected int colUsing;
    protected int x, y, width, heigth, WIDTH, HEIGHT, movingVectorX, movingVectorY, rowCount, colCount;
    protected int XCenter;
    protected int YCenter;
    protected double movingVectorLength;

    protected boolean isRunning;

    protected Bitmap image;
    protected Bitmap[] walkAnimation;
    protected GameSurface gameSurface;


    public BaseGameObject(int x, int y, int rowCount, int colCount, Bitmap image, GameSurface gameSurface) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.gameSurface = gameSurface;

        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();
        width = WIDTH / colCount;
        heigth = HEIGHT / rowCount;

        walkAnimation = new Bitmap[colCount];
        for (int i = 0; i < colCount; i++) {
            walkAnimation[i] = createSubimage(0, i);
        }
    }

    public void update(float deltaTime) {
        if (isRunning()) {
            colUsing++;
            if (colUsing >= colCount) {
                colUsing = 0;
            }

            float distance = velocity * deltaTime;

            movingVectorLength = Math.sqrt(
                    movingVectorX * movingVectorX +
                            movingVectorY * movingVectorY);

            this.x = x + (int) (distance * movingVectorX / movingVectorLength);
            this.y = y + (int) (distance * movingVectorY / movingVectorLength);

            XCenter = getX() - getWidth() / 2;
            YCenter = getY() - getHeigth() / 2;

        }
    }

    public void rotate(float pointX, float pointY, float addRotate) {
        float rotation;
        int deltaX = (int) pointX - XCenter;
        int deltaY = (int) pointY - YCenter;
        rotation = (float) -Math.atan2(deltaX, deltaY);
        rotation = (float) Math.toDegrees(rotation) + 180;
        angle = rotation - addRotate;
    }

    private Bitmap getCurrentMoveBitmap() {
        Bitmap rotatedBitmap = walkAnimation[colUsing];


        return rotateBitmap(rotatedBitmap, angle);
    }

    private Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
    }

    protected Bitmap createSubimage(int row, int col) {
        return Bitmap.createBitmap(image, col * width, row * heigth, width, heigth);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setMovingVectors(int moveX, int moveY) {
        movingVectorX = moveX;
        movingVectorY = moveY;
    }

    public double getMovingVectorLength() {
        return movingVectorLength;
    }

    public void setMovingVectorLength(double movingVectorLength) {
        this.movingVectorLength = movingVectorLength;
    }

    public int getXCenter() {
        return XCenter;
    }

    public void setXCenter(int XCenter) {
        this.XCenter = XCenter;
    }

    public int getYCenter() {
        return YCenter;
    }

    public void setYCenter(int YCenter) {
        this.YCenter = YCenter;
    }

    public int getMovingVectorX() {
        return movingVectorX;
    }

    public int getMovingVectorY() {
        return movingVectorY;
    }
}
