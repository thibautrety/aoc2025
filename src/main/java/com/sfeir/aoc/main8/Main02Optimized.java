package com.sfeir.aoc.main8;

import com.sfeir.aoc.graph.Forest;
import com.sfeir.aoc.graph.Graph;
import com.sfeir.aoc.utils.Connection;
import com.sfeir.aoc.utils.ReadFile;
import com.sfeir.aoc.utils.Triple;
import java.util.Map;
import java.util.Set;

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

    private static void removeKey(Triple maxKey, Map<Triple, Set<Triple>> linkedTriples) {
        var ToRemove = linkedTriples.get(maxKey);
        ToRemove.forEach(linkedTriples::remove);
        linkedTriples.remove(maxKey);
    }

    private static Triple findMax(Map<Triple, Set<Triple>> linkedTriples) {
        Triple result = null;
        int maxSize = Integer.MIN_VALUE;
        for (var entry : linkedTriples.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
                result = entry.getKey();
            }
        }
        return result;
    }

    private static Connection findShortest(Set<Triple> triples, Set<Connection> connections) {
        Connection result = null;
        double minDistance = Double.MAX_VALUE;
        for (Triple t1 : triples) {
            for (Triple t2 : triples) {
                if (!t1.equals(t2) && !connections.contains(new Connection(t1, t2)) && !connections.contains(new Connection(t2, t1))) {
                    var currentDistance = t1.distance(t2);
                    if (currentDistance < minDistance) {
                        minDistance = currentDistance;
                        result = new Connection(t1, t2);
                    }
                }
            }
        }
        return result;
    }

    private static Triple min(Set<Triple> triples, Triple source, Set<Triple> excluded) {
        double minDistance = Double.MAX_VALUE;
        Triple result = null;
        for (Triple t : triples) {
            if (!t.equals(source) && !excluded.contains(t)) {
                var current = t.distance(source);
                if (current < minDistance) {
                    minDistance = current;
                    result = t;
                }
            }
        }

        return result;
    }

}


