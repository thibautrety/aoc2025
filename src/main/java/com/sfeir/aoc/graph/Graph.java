package com.sfeir.aoc.graph;

import com.sfeir.aoc.utils.Triple;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Graph {
    Set<Triple> points = new HashSet<>();
    private Triple elected;
    public Graph(Triple triple){
        elected = triple;
        points.add(elected);
    }

    public Triple getElected(){
        return elected;
    }

    public void merge(Graph other){
        points.addAll(other.points);
    }

    public boolean contains(Triple point) {
        return points.contains(point);
    }

    public int size(){
        return points.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(elected, graph.elected);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elected);
    }
}
