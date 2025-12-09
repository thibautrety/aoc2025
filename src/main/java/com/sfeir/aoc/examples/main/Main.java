package com.sfeir.aoc.examples.main;

import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.utils.ReadFile;

public class Main {
    public static void main(String[] args) {
        var result = GraphBuilder.dijkstraBuildGraph("src/main/resources/dijsktra_example.txt", 13, 1, 1, 13, '#');
        char[][] grid = ReadFile.readGridReverse("src/main/resources/dijsktra_example.txt");
        ReadFile.showGrid(grid);

        System.out.println(result.distance);
        while (!result.parents.isEmpty()) {
            grid[result.coord.i()][result.coord.j()] = '@';
            result = result.parents.iterator().next();
        }
        ReadFile.showGrid(grid);
    }
}
