package com.java.model.flyweight.chess;

import com.java.model.flyweight.chess.impl.BlackChess;
import com.java.model.flyweight.chess.impl.WhiteChess;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ning
 * @date Create in 2019/4/19
 */
public class ChessFactory {

    // 静态初始化棋子工厂, 补充棋子对象到工厂
    static {
        new ChessFactory();
    }

    private static Map<String, BaseChess> chessManMap;

    private ChessFactory(){

        chessManMap = new HashMap<>(4);
        BaseChess whiteChess = new WhiteChess();
        BaseChess blackChess = new BlackChess();
        chessManMap.put("black", blackChess);
        chessManMap.put("white", whiteChess);
    }

    public static BaseChess getChess(String name) {

        return chessManMap.get(name);
    }
}
