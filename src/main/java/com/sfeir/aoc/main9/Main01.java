package com.sfeir.aoc.main9;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.utils.Connection;
import com.sfeir.aoc.utils.ReadFile;
import com.sfeir.aoc.utils.Triple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main01 {
    public static void main() {
        compute("src/main/resources/input_09example.txt", 10);
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_09.txt", 1000);
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName, int shortedCount) {
        var lines = ReadFile.readInputFileLines(fileName);
        List<Coord> coords = new ArrayList<>();
        long max = 0L;
        for (String line : lines) {
            var coordsAsString = line.split(",");
            coords.add(new Coord(Integer.parseInt(coordsAsString[0]), Integer.parseInt(coordsAsString[1])));
        }
        for(Coord coord : coords){
            for(Coord otherCoord : coords){
                if(!coord.equals(otherCoord)){
                    long area =getRectangleArea(coord, otherCoord);
                    if(area > max){
                        max = area;
                    }
                }
            }
        }
        System.out.println("Max area: " + max);
    }

    public static long getRectangleArea(Coord a, Coord b){
        long length = Math.abs(a.i() - b.i())+1;
        long width = Math.abs(a.j() - b.j())+1;
        return length * width;
    }

}


