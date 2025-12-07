package com.sfeir.aoc.examples.main;

import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.utils.ReadFile;

public class Main {
    public static void main(String[] args) {
        var result = GraphBuilder.dijkstraBuildGraph("src/main/resources/dijsktra_example.txt", 13, 1, 1, 13, '#');
        char[][] grid = ReadFile.readGrid("src/main/resources/dijsktra_example.txt");
        System.out.println(result.distance);
        while (!result.parents.isEmpty()) {
            grid[result.coord.j()][result.coord.i()] = '@';
            result = result.parents.iterator().next();
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println("\r");
        }
    }
}
