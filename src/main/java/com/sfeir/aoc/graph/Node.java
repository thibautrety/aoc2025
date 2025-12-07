package com.sfeir.aoc.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Node {
    public String name;
    public Coord coord;
    public List<Edge> edges;
    public double distance;
    public Set<Node> parents;
    public long count;

    public Node(int i, int j){
        this.name = i+";"+j;
        this.edges = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
        this.parents = new HashSet<>();
        this.coord = new Coord(i,j);
        count = 1l;
    }

    public void addParent(Node parent){
        this.parents.add(parent);
        count = parents.stream().mapToLong(n -> n.count).sum();
    }

    public int getCost(){
        if(parents.isEmpty()){
            return 1;
        }
        else{
            return parents.stream().mapToInt(Node::getCost).sum();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(coord, node.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coord);
    }

    public void increment(int value) {
        count += value;
        for(Edge edge : edges){
            edge.target.increment(value);
        }
    }
}
