package com.sfeir.aoc.main9;

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
        compute("src/main/resources/input_08example.txt", 10);
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_08.txt", 1000);
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName, int shortedCount) {
        var lines = ReadFile.readInputFileLines(fileName);
        Set<Triple> triples = new HashSet<>();
        for (String line : lines) {
            var coordsAsString = line.split(",");
            triples.add(new Triple(Integer.parseInt(coordsAsString[0]), Integer.parseInt(coordsAsString[1]), Integer.parseInt(coordsAsString[2])));
        }

        Set<Connection> connections = new HashSet<>();
        Map<Triple, Set<Triple>> linkedTriples = new HashMap<>();
        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < shortedCount; i++) {
            Connection shortest = findShortest(triples, connections);
            if (!linkedTriples.containsKey(shortest.a())) {
                linkedTriples.put(shortest.a(), new HashSet<>());
            }
            if (!linkedTriples.containsKey(shortest.b())) {
                linkedTriples.put(shortest.b(), new HashSet<>());
            }

            if (linkedTriples.containsKey(shortest.a())) {
                linkedTriples.get(shortest.a()).add(shortest.b());
                linkedTriples.get(shortest.a()).forEach(t -> {
                    if(!linkedTriples.containsKey(t)){
                        linkedTriples.put(t, new HashSet<>());
                    }
                    linkedTriples.get(t).add(shortest.a());
                    linkedTriples.get(t).addAll(linkedTriples.get(shortest.a()));
                });
            }
            if (linkedTriples.containsKey(shortest.b())) {
                linkedTriples.get(shortest.b()).add(shortest.a());
                linkedTriples.get(shortest.b()).forEach(t -> {
                    if(!linkedTriples.containsKey(t)){
                        linkedTriples.put(t, new HashSet<>());
                    }
                    linkedTriples.get(t).add(shortest.b());
                    linkedTriples.get(t).addAll(linkedTriples.get(shortest.b()));
                });
            }
            connections.add(shortest);
            if(i%100==0) {
                System.out.println("processing..." + i);
            }
        }

        Triple maxKey = findMax(linkedTriples);
        long max1 = linkedTriples.get(maxKey).size();
        removeKey(maxKey, linkedTriples);

        maxKey = findMax(linkedTriples);
        long max2 = linkedTriples.get(maxKey).size();
        removeKey(maxKey, linkedTriples);

        maxKey = findMax(linkedTriples);
        long max3 = linkedTriples.get(maxKey).size();
        removeKey(maxKey, linkedTriples);
        long res = max1*max2*max3;
        System.out.println(res);

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


