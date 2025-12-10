package com.sfeir.aoc.graph;

public record Coord(int i, int j) {

    public String reverse() {
        return j + "," + i;
    }
}
