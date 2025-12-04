package com.sfeir.aoc.main4;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.utils.ReadFile;
import java.util.HashSet;
import java.util.Set;

public class Main02 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_04.txt";
        char[][] grid= ReadFile.readGrid(fileName);
        var coords = getCount(grid);
        int coordCount = coords.size();
        var result = coordCount;
        while(coordCount>0){
            for(var coord : coords){
                grid[coord.i()][coord.j()]='.';
            }
            coords = getCount(grid);
            coordCount = coords.size();
            result += coordCount;
        }

        System.out.println(result);
    }

    private static Set<Coord> getCount(char[][] grid) {
        int count=0;
        Set<Coord> coords = new HashSet<>();
        for(int i = 0; i< grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]=='@'){
                    var adj = 0;
                   if(i+1< grid.length && grid[i+1][j]=='@') {
                       adj++;
                   }
                   if(i+1< grid.length && j+1 < grid[i].length && grid[i+1][j+1]=='@') {
                       adj++;
                   }
                   if(i+1< grid.length && j-1 >=0 && grid[i+1][j-1]=='@') {
                       adj++;
                   }

                   if(i-1>=0 && grid[i-1][j]=='@') {
                       adj++;
                   }
                   if(i-1>=0 && j+1 < grid[i].length && grid[i-1][j+1]=='@') {
                       adj++;
                   }
                   if(i-1>=0 && j-1 >=0 && grid[i-1][j-1]=='@') {
                       adj++;
                   }

                   if(j+1 < grid[i].length && grid[i][j+1]=='@') {
                       adj++;
                   }
                   if(j-1 >=0 && grid[i][j-1]=='@') {
                       adj++;
                   }

                   if(adj<4){
                       coords.add(new Coord(i,j));
                       count++;
                   }
                }
            }
        }
        return coords;
    }
}