package com.sfeir.aoc.utils;

public record Connection(Triple a, Triple b) {
    public double distance () {
        return a.distance(b);
    }
}
