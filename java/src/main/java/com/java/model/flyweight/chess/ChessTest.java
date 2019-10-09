package com.java.model.flyweight.chess;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public class ChessTest {

    public static void main(String[] args) {

        // 这里直接调用静态方法获取棋子对象
        BaseChess black1 = ChessFactory.getChess("black");
        BaseChess black2 = ChessFactory.getChess("black");
        BaseChess white = ChessFactory.getChess("white");

        System.out.println(black1 == black2);

        black1.display(new Coordinates(1, 2));
        black2.display(new Coordinates(2, 4));
        white.display(new Coordinates(3, 1));

        System.out.println(black1 == black2);

    }
}
