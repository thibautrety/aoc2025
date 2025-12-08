package com.sfeir.aoc.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {

    public static List<String> readInputFileLines(String fileName) {
        try{
            return Files.readAllLines(Path.of(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    public static char[][] readGrid(String fileName) {
        try{
            var lines = Files.readAllLines(Path.of(fileName));
            char [][] result = new char[lines.getFirst().length()][lines.size()];
            for(int j = 0; j<lines.size(); j++){
                for(int i = 0; i<lines.get(j).length(); i++){
                    result[i][j] = lines.get(j).charAt(i);
                }
                lines.set(j, lines.get(j).trim());
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    public static char[][] readGridReverse(String fileName) {
        try{
            var lines = Files.readAllLines(Path.of(fileName));
            char [][] result = new char[lines.size()][lines.getFirst().length()];
            for(int j = 0; j<lines.size(); j++){
                for(int i = 0; i<lines.get(j).length(); i++){
                    result[j][i] = lines.get(j).charAt(i);
                }
                lines.set(j, lines.get(j).trim());
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    public static void showGrid(char[][] grid) {
        for(int i = 0; i< grid.length; i++) {
            for(int j = 0; j< grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

}
