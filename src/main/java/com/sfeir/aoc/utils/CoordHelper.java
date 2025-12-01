package com.sfeir.aoc.utils;

public class CoordHelper {

    public static boolean inside(int x, int y, int w, int h){
        return x>=0 && y >=0 &&x<w && y<h;
    }
}
