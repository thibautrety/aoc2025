package com.sfeir.aoc.utils;

public record Triple(int a, int b, int c) {

    public double distance(Triple triple){
        return Math.sqrt(Math.pow(triple.a()-a, 2) + Math.pow(triple.b()-b, 2) +Math.pow(triple.c()-c, 2));
    }
}
