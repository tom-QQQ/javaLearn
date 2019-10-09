package com.java.model.flyweight.chess;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public abstract class BaseChess {

    /**
     * 获取棋子颜色
     * @return 棋子颜色
     */
    public abstract String getColor();

    public void display(Coordinates coordinates) {

        System.out.println("棋子颜色: " + getColor() + ", 棋子坐标为: " + coordinates.getCoordinates());
    }
}
