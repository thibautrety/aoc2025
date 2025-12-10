package com.sfeir.aoc.main9;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.utils.ReadFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main02 {
    public static void main() {
//        compute("src/main/resources/input_09example.txt", 10, true);
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_09.txt", 1000, false);
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        //1796555872
        //1284556727
        //1671059740
        // 1671059740
        //1670952834
        // 1665698145
    }

    private static Coord findWhiteRabbitUp(List<Coord> coords, Set<Coord> isGreen){
        int minI = coords.stream().mapToInt(Coord::i).min().getAsInt();
        int minJ = coords.stream().mapToInt(Coord::j).min().getAsInt();
        Coord initial = new Coord(minI, minJ);
        while(!isGreen.contains(initial)){
            initial = new Coord(initial.i(), initial.j()+1);
        }
        while(isGreen.contains(initial)){
            initial = new Coord(initial.i()+1, initial.j());
        }
        while(!isGreen.contains(initial)){
            initial = new Coord(initial.i()+1, initial.j());
        }

        while(isGreen.contains(initial)){
            initial = new Coord(initial.i(), initial.j()+1);
        }

        Coord initial2 = new Coord(initial.i()-1, initial.j());
        while(isGreen.contains(initial2)){
            initial2 = new Coord(initial2.i()-1, initial2.j());
        }
        while(!isGreen.contains(initial2)){
            initial2 = new Coord(initial2.i()-1, initial2.j());
        }

        Coord initial3 = new Coord(initial2.i(), initial2.j());
        while(isGreen.contains(initial3)){
            initial3 = new Coord(initial3.i(), initial3.j()-1);
        }
        while(!isGreen.contains(initial3)){
            initial3 = new Coord(initial3.i(), initial3.j()-1);
        }
        while(isGreen.contains(initial3)){
            initial3 = new Coord(initial3.i(), initial3.j()-1);
        }

        System.out.println("FINAAAAL " + getRectangleArea(initial, initial3));
        System.out.println("FINAAAAL " + initial.reverse() + initial3.reverse());


        return initial;
    }

    private static Coord findWhiteRabbitDown(List<Coord> coords, Set<Coord> isGreen){
        int maxI = coords.stream().mapToInt(Coord::i).max().getAsInt();
        int maxJ = coords.stream().mapToInt(Coord::j).max().getAsInt();
        Coord initial = new Coord(maxI, maxJ);
        while(!isGreen.contains(initial)){
            initial = new Coord(initial.i(), initial.j()-1);
        }
        while(isGreen.contains(initial)){
            initial = new Coord(initial.i()-1, initial.j());
        }
        while(!isGreen.contains(initial)){
            initial = new Coord(initial.i()-1, initial.j());
        }

        while(isGreen.contains(initial)){
            initial = new Coord(initial.i(), initial.j()+1);
        }
        initial = new Coord(initial.i(), initial.j()-1);


        Coord initial2 = new Coord(initial.i()+1, initial.j());
        while(isGreen.contains(initial2)){
            initial2 = new Coord(initial2.i()+1, initial2.j());
        }
        while(!isGreen.contains(initial2)){
            initial2 = new Coord(initial2.i()+1, initial2.j());
        }

        Coord initial3 = new Coord(initial2.i(), initial2.j());
        while(isGreen.contains(initial3)){
            initial3 = new Coord(initial3.i(), initial3.j()-1);
        }
        while(!isGreen.contains(initial3)){
            initial3 = new Coord(initial3.i(), initial3.j()-1);
        }

//        while(isGreen.contains(initial3)){
//            initial3 = new Coord(initial3.i(), initial3.j()-1);
//        }
//
        while(!coords.contains(initial3)){
            initial3 = new Coord(initial3.i()-1, initial3.j());
        }

        System.out.println("FINAAAAL DOWN " + getRectangleArea(initial, initial3));
        System.out.println("FINAAAAL DOWN " + initial.reverse() +"; " + new Coord(initial3.i(), initial.j()).reverse() +";"+ initial3.reverse() + ";"+new Coord(initial.i(), initial3.j()).reverse());


        return initial;
    }

    private static void compute(String fileName, int shortedCount, boolean display) {
        var lines = ReadFile.readInputFileLines(fileName);
        List<Coord> coords = new ArrayList<>();
        long max = 0L;
        for (String line : lines) {
            var coordsAsString = line.split(",");
            coords.add(new Coord(Integer.parseInt(coordsAsString[1]), Integer.parseInt(coordsAsString[0])));
        }
        var isGreen = perimetre(coords, display);
        Coord whiteRabbit = findWhiteRabbitUp(coords, isGreen);
        findWhiteRabbitDown(coords, isGreen);
        System.out.println("Max area: " + max);
    }


    private static boolean allGreen(Coord coord, Coord otherCoord, List<Coord> coords, List<Coord> isGreen, Map<Coord, Boolean> memo) {
        return isGreen(new Coord(coord.i(), otherCoord.j()), coords, isGreen, memo)
                && isGreen(new Coord(otherCoord.i(), coord.j()), coords, isGreen, memo)
                && isGreen(new Coord(coord.i(), coord.j()), coords, isGreen, memo)
                && isGreen(new Coord(otherCoord.i(), otherCoord.j()), coords, isGreen, memo);
    }


    public static List<Coord> orderOrthogonalPolygon(Set<Coord> points) {
        List<Coord> ordered = new ArrayList<>();
        Set<Coord> remaining = new HashSet<>(points);
        Coord current = points.stream()
                .min(Comparator.comparingInt(Coord::i).thenComparingInt(Coord::j))
                .get();
        ordered.add(current);
        remaining.remove(current);

        while (!remaining.isEmpty()) {
            Coord finalCurrent = current;
            Coord finalCurrent1 = current;
            Coord next = remaining.stream()
                    .filter(p -> (p.i() == finalCurrent.i() || p.j() == finalCurrent.j()))
                    .min(Comparator.comparingInt(p -> Math.abs(p.i() - finalCurrent1.i()) + Math.abs(p.j() - finalCurrent1.j())))
                    .orElse(null);
            if (next == null) break;
            ordered.add(next);
            remaining.remove(next);
            current = next;
        }
        return ordered;
    }


    public static boolean isInsidePolygon(List<Coord> polygon, Coord point) {
        int intersections = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Coord a = polygon.get(i);
            Coord b = polygon.get((i + 1) % polygon.size());
            // Teste si le segment [a,b] croise la ligne horizontale passant par point
            if ((a.j() > point.j()) != (b.j() > point.j())) {
                int xIntersect = a.i() + (point.j() - a.j()) * (b.i() - a.i()) / (b.j() - a.j());
                if (xIntersect > point.i()) {
                    intersections++;
                }
            }
        }
        return intersections % 2 == 1;
    }

    private static Set<Coord> perimetre(List<Coord> coords, boolean display) {
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

        if(display) {
            display(coords, maxI, maxJ, greenCoords);
        }
        return greenCoords;
    }

    private static void display(List<Coord> coords, int maxI, int maxJ, Set<Coord> greenCoords) {
        for (int i = 0; i < maxI +1; i++) {

            for (int j = 0; j < maxJ +1; j++) {
                if (coords.contains(new Coord(i, j))) {
                    System.out.print('#');
                } else if (greenCoords.contains(new Coord(i, j))) {
                    System.out.print('X');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    private static boolean isGreen(Coord coord, List<Coord> coords, List<Coord> isGreen, Map<Coord, Boolean> memo) {
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

//        int intersect = 0;
//        int adjacentCount = 0;
//        for (int j = coord.j(); j >= minJ-1; j--) {
//            if (isGreen.contains(new Coord(coord.i(), j))) {
//                adjacentCount ++;
//                if(adjacentCount == 0){
//                    intersect++;
//                }
//            }
//            else{
//                if(adjacentCount>1){
//                    intersect++;
//                }
//                adjacentCount = 0;
//            }
//        }
//
//        for (int j = coord.j(); j >= minJ-1; j--) {
//            if (isGreen.contains(new Coord(coord.i(), j))) {
//                adjacentCount ++;
//                if(adjacentCount == 0){
//                    intersect++;
//                }
//            }
//            else{
//                if(adjacentCount>1){
//                    intersect++;
//                }
//                adjacentCount = 0;
//            }
//        }
//
//
//
        boolean insidePolygon = isInsidePolygon(isGreen, coord);
        memo.put(coord, insidePolygon);
        return insidePolygon;

    }

    public static long getRectangleArea(Coord a, Coord b) {
        long length = Math.abs(a.i() - b.i()) + 1;
        long width = Math.abs(a.j() - b.j()) + 1;
        return length * width;
    }

}


