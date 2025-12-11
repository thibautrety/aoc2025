package com.sfeir.aoc.graph;

public class EdgeLight {
    public NodeLight target;
    public double cost;
    public EdgeLight(NodeLight target, double cost){
        this.target = target;
        this.cost = cost;
    }
}
