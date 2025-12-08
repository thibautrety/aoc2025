package com.sfeir.aoc.graph;

import com.sfeir.aoc.utils.Connection;
import com.sfeir.aoc.utils.Triple;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Forest {
    public Set<Graph> graphs = new HashSet<>();

    private Map<Triple, Graph> pointGraphMap = new HashMap<>();
    public void addGraph(Graph onePointGraph) {
        graphs.add(onePointGraph);
        pointGraphMap.put(onePointGraph.getElected(), onePointGraph);
    }

    public void addEdge(Triple a, Triple b){
        Graph bGraph = getGraphContaining(b);
        Graph graphContainingA = getGraphContaining(a);
        graphContainingA.merge(bGraph);
        bGraph.points.forEach(t -> pointGraphMap.put(t, graphContainingA));
        graphs.remove(bGraph);
    }

    public Graph getGraphContaining(Triple point){
        return pointGraphMap.get(point);
    }

    public Connection findShortest() {
        Connection result = null;
        double minDistance = Double.MAX_VALUE;
        Set<Graph> handled = new HashSet<>();
        for (Graph g : graphs) {
            for(Graph other : graphs){
                if(!g.equals(other) && !handled.contains(other)){
                    var connection = findShortestBetweenGraphs(g, other);
                    if(connection != null){
                        var currentDistance = connection.distance();
                        if (currentDistance < minDistance) {
                            minDistance = currentDistance;
                            result = connection;
                        }
                    }
                }
            }
            handled.add(g);
        }
        return result;
    }

    private Connection findShortestBetweenGraphs(Graph g, Graph other) {
        Connection result = null;
        double minDistance = Double.MAX_VALUE;
        for (Triple t1 : g.points) {
            for (Triple t2 : other.points) {
                double currentDistance = t1.distance(t2);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    result = new Connection(t1, t2);

                }
            }
        }
        return result;
    }

    public void addEdge(Connection connection) {
       addEdge(connection.a(), connection.b());
    }
}
