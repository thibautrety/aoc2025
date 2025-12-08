package com.sfeir.aoc.main8;

import com.sfeir.aoc.graph.Forest;
import com.sfeir.aoc.graph.Graph;
import com.sfeir.aoc.utils.Connection;
import com.sfeir.aoc.utils.ReadFile;
import com.sfeir.aoc.utils.Triple;

public class Main02Optimized {
    public static void main() {
        compute("src/main/resources/input_08example.txt", 10);
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_08.txt", 1000);
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName, int shortedCount) {
        var lines = ReadFile.readInputFileLines(fileName);
        Forest forest = new Forest();
        for (String line : lines) {
            var coordsAsString = line.split(",");
            Graph onePointGraph = new Graph(new Triple(Integer.parseInt(coordsAsString[0]), Integer.parseInt(coordsAsString[1]), Integer.parseInt(coordsAsString[2])));
            forest.addGraph(onePointGraph);
        }
        Connection connection = null;
        int i = 0;
        while(forest.graphs.size()>1) {
            connection = forest.findShortest();
            forest.addEdge(connection);
            if(i%100 == 0){
                System.out.println("i = "+i);
            }
            i++;
        }
        System.out.println(connection.a().a()*connection.b().a());
    }
}


