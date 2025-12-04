package com.sfeir.aoc.main4;

import com.sfeir.aoc.utils.ReadFile;

public class Main01 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_04.txt";
        char[][] grid= ReadFile.readGrid(fileName);
        int count = getCount(grid);

        System.out.println(count);
    }

    private static int getCount(char[][] grid) {
        int count=0;
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
                       count++;
                   }
                }
            }
        }
        return count;
    }
}