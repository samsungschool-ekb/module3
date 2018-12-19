package belenkov.samsung.ru.gametestgroup3.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BaseGameObject {
    protected int x, y;
    protected int width, heigth;
    protected int WIDTH, HEIGHT;
    protected Bitmap image;
    protected int rowCount, colCount;


    public BaseGameObject(int x, int y, Bitmap image, int rowCount, int colCount) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;

        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();

        width = WIDTH / colCount;
        heigth = HEIGHT / rowCount;
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

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }
}
