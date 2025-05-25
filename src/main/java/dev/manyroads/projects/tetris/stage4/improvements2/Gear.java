package dev.manyroads.projects.tetris.stage4.improvements2;

import java.util.HashMap;
import java.util.Map;

public abstract class Gear {

    static Integer[][] O = {{4, 14, 15, 5}};
    static Integer[][] I = {{4, 14, 24, 34}, {3, 4, 5, 6}};
    static Integer[][] S = {{5, 4, 14, 13}, {4, 14, 15, 25}};
    static Integer[][] Z = {{4, 5, 15, 16}, {5, 15, 14, 24}};
    static Integer[][] L = {{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}};
    static Integer[][] J = {{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}};
    static Integer[][] T = {{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}};

    public static Map<String, Integer[][]> setPieceMap() {
        Map<String, Integer[][]> pieceMap = new HashMap<>();
        pieceMap.put("O", O);
        pieceMap.put("I", I);
        pieceMap.put("S", S);
        pieceMap.put("Z", Z);
        pieceMap.put("L", L);
        pieceMap.put("J", J);
        pieceMap.put("T", T);
        return pieceMap;
    }

}
