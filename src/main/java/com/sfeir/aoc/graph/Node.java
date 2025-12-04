package com.sfeir.aoc.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String name;
    public Coord coord;
    public List<Edge> edges;
    public double distance;
    public Node parent;

    public Node(int i, int j){
        this.name = i+";"+j;
        this.edges = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
        this.parent = null;
        this.coord = new Coord(i,j);
    }

    @Override
    public String toString() {
        return name;
    }
}
