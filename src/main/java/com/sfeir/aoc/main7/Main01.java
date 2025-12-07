package com.sfeir.aoc.main7;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.graph.Node;
import com.sfeir.aoc.utils.ReadFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main01 {
    public static void main(String[] args) {

//        compute("src/main/resources/input_07example.txt", 0, 7, 15, 14);
        compute("src/main/resources/input_07.txt", 0, 70, 141, 140);
    }

    static Set<Coord> visited = new HashSet<>();
    static Set<Coord> visitedPaths = new HashSet<>();
    private static void compute(String fileName, int startI, int startJ, int maxI, int maxJ) {
        var grid = ReadFile.readGridReverse(fileName);
        downStep(grid, startI, startJ, maxI, maxJ);
        System.out.println("Total paths: " + visited.size());
        for(int i = 0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(visited.contains(new Coord(i,j))){
                    System.out.print("X");
                } else if (visitedPaths.contains(new Coord(i,j))){
                    System.out.print('|');
                }
                else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void downStep(char[][] grid, int startI, int startJ, int maxI, int maxJ) {
        if(visitedPaths.contains(new Coord(startI, startJ))) {
            return;
        }
        visitedPaths.add(new Coord(startI, startJ));
        if(startJ>=grid.length || startJ<0){
            return;
        }
        if (startI < grid[0].length) {
            if (grid[startI + 1][startJ] != '^') {
                downStep(grid, startI + 1, startJ, maxI, maxJ);
            } else {
                visited.add(new Coord(startI+1, startJ));
                downStep(grid, startI+1, startJ + 1, maxI, maxJ);
                downStep(grid, startI+1, startJ - 1, maxI, maxJ);
            }
        }
    }
}


