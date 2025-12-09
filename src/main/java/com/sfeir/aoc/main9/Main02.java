package com.sfeir.aoc.main9;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.utils.ReadFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main02 {
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
            coords.add(new Coord(Integer.parseInt(coordsAsString[1]), Integer.parseInt(coordsAsString[0])));
        }
        var isGreen = perimetre(coords);
        System.out.println("Green coords size: " + isGreen.size());
        Map<Coord, Boolean> memo = new HashMap<>();
        for (Coord coord : coords) {
            for (Coord otherCoord : coords) {
                if (!coord.equals(otherCoord)) {
                    long area = getRectangleArea(coord, otherCoord);
                    if (area > max && allGreen(coord, otherCoord, coords, isGreen, memo)) {
                        max = area;
                    }
                }
            }
        }
        System.out.println("Max area: " + max);
    }


    private static boolean allGreen(Coord coord, Coord otherCoord, List<Coord> coords, Set<Coord> isGreen, Map<Coord, Boolean> memo) {
        return isGreen(new Coord(coord.i(), otherCoord.j()), coords, isGreen, memo)
                && isGreen(new Coord(otherCoord.i(), coord.j()), coords, isGreen, memo)
                && isGreen(new Coord(coord.i(), coord.j()), coords, isGreen, memo)
                && isGreen(new Coord(otherCoord.i(), otherCoord.j()), coords, isGreen, memo);
    }

    private static Set<Coord> perimetre(List<Coord> coords) {
        int minI = coords.stream().mapToInt(Coord::i).min().getAsInt();
        int maxI = coords.stream().mapToInt(Coord::i).max().getAsInt();
        int minJ = coords.stream().mapToInt(Coord::j).min().getAsInt();
        int maxJ = coords.stream().mapToInt(Coord::j).max().getAsInt();
        Set<Coord> greenCoords = new java.util.HashSet<>();
        for (int i = minI; i <= maxI; i++) {
            int finalI = i;
            var icoords = coords.stream().filter(c -> c.i() == finalI).toList();
            if (icoords.size() >= 2) {
                var minIJ = icoords.stream().mapToInt(Coord::j).min().getAsInt();
                var maxIJ = icoords.stream().mapToInt(Coord::j).max().getAsInt();
                for (int j = minIJ; j <= maxIJ; j++) {
                    greenCoords.add(new Coord(finalI, j));
                }
            }
        }
        for (int j = minJ; j <= maxJ; j++) {
            int finalJ = j;
            var jcoords = coords.stream().filter(c -> c.j() == finalJ).toList();
            if (jcoords.size() >= 2) {
                var minIJ = jcoords.stream().mapToInt(Coord::i).min().getAsInt();
                var maxIJ = jcoords.stream().mapToInt(Coord::i).max().getAsInt();
                for (int i = minIJ; i <= maxIJ; i++) {
                    greenCoords.add(new Coord(i, finalJ));
                }
            }
        }
        return greenCoords;
    }

    private static boolean isGreen(Coord coord, List<Coord> coords, Set<Coord> isGreen, Map<Coord, Boolean> memo) {
        if (isGreen.contains(coord)) {
            return true;
        }
        if (memo.containsKey(coord)) {
            return memo.get(coord);
        }
        var maxI = coords.stream().mapToInt(Coord::i).max().getAsInt();
        var minI = coords.stream().mapToInt(Coord::i).min().getAsInt();
        var maxJ = coords.stream().mapToInt(Coord::j).max().getAsInt();
        var minJ = coords.stream().mapToInt(Coord::j).min().getAsInt();
        int minIntersect = 0;
        boolean found = false;
        for (int i = coord.i(); i > minI - 2; i--) {
            if (isGreen.contains(new Coord(i, coord.j()))) {
                found = true;
                break;
            }
        }
        if (!found) {
            memo.put(coord, found);
            return found;
        }

        found = false;
        for (int i = coord.i(); i < maxI + 2; i++) {
            if (isGreen.contains(new Coord(i, coord.j()))) {
                found = true;
                break;
            }
        }
        if (!found) {
            memo.put(coord, found);
            return found;
        }

        found = false;
        for (int j = coord.j(); j < maxJ + 2; j++) {
            if (isGreen.contains(new Coord(coord.i(), j))) {
                found = true;
                break;
            }
        }
        if (!found) {
            memo.put(coord, found);
            return found;
        }

        found = false;
        for (int j = coord.j(); j > minJ - 2; j--) {
            if (isGreen.contains(new Coord(coord.i(), j))) {
                found = true;
                break;
            }
        }
        if (!found) {
            memo.put(coord, found);
            return found;
        }

        int intersect = 0;
        int adjacentCount = 0;
        for (int j = coord.j(); j > minJ - 2; j--) {
            if (isGreen.contains(new Coord(coord.i(), j))) {
                adjacentCount ++;
                if(adjacentCount == 0){
                    intersect++;
                }
            }
            else{
                if(adjacentCount>1){
                    intersect++;
                }
                adjacentCount = 0;
            }
        }



        memo.put(coord, intersect%2==1);
        return intersect%2==1;

    }

    public static long getRectangleArea(Coord a, Coord b) {
        long length = Math.abs(a.i() - b.i()) + 1;
        long width = Math.abs(a.j() - b.j()) + 1;
        return length * width;
    }

}


