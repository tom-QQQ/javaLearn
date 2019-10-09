package com.java.model.flyweight.chess;

/**
 * 棋子坐标
 * @author Ning
 * @date Create in 2019/4/19
 */
public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
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

    public String getCoordinates() {
        return x + ", " + y;
    }
}
