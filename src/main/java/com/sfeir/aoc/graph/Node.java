package com.sfeir.aoc.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String name;
    public List<Edge> edges;
    public double distance;
    public Node parent;

    public Node(String name){
        this.name = name;
        this.edges = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
        this.parent = null;
    }

    @Override
    public String toString() {
        return name;
    }
}
