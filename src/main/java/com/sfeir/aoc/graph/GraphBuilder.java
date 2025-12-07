package com.sfeir.aoc.graph;

import com.sfeir.aoc.utils.ReadFile;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphBuilder {

    public static Node buildGraph(String filename, int startI, int startJ, char wall){
        return buildMaze(ReadFile.readGrid(filename),startI,startJ, wall);
    }

    public static Node dijkstraBuildGraph(String filename, int startI, int startJ, int endI, int endJ, char wall){
        var currentNode = buildMaze(ReadFile.readGrid(filename),startI,startJ, wall);
        currentNode.distance = 0.0;
        Queue<Node> toHandle = new LinkedList<>();
        toHandle.add(currentNode);
        Map<Coord, Node> allVisitedNodes = new HashMap<>();
        while(!toHandle.isEmpty()){
            currentNode = toHandle.poll();
            allVisitedNodes.put(currentNode.coord, currentNode);
            var edges = currentNode.edges;
            for(Edge edge : edges){
                var neighbor = edge.target;
                double newDist = currentNode.distance + edge.cost;
                if(newDist < neighbor.distance){
                    neighbor.distance = newDist;
                    neighbor.parents.add(currentNode);
                    toHandle.remove(neighbor);
                    toHandle.add(neighbor);
                }
            }
        }
        return allVisitedNodes.get(new Coord(endI, endJ));

    }

    public static Node buildMaze(char[][] input,int startI, int startJ,  char wall){
        int rows = input.length;
        int cols = input[0].length;
        Map<Coord, Node> visited = new HashMap<>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(input[r][c] != wall){
                    Node node = new Node(c,r);
                    if(!visited.containsKey(node.coord)) {
                        visited.put(node.coord, node);
                    }
                }
            }
        }

        for(Node node : visited.values()){
            var currentCoord = node.coord;
            Coord left = new Coord(currentCoord.i() - 1, currentCoord.j());
            if(visited.containsKey(left)){
                node.edges.add(new Edge(visited.get(left),1.0));
            }

            Coord right = new Coord(currentCoord.i() + 1, currentCoord.j());
            if(visited.containsKey(right)){
                node.edges.add(new Edge(visited.get(right),1.0));
            }

            Coord bottom = new Coord(currentCoord.i(), currentCoord.j()+1);
            if(visited.containsKey(bottom)){
                node.edges.add(new Edge(visited.get(bottom),1.0));
            }

            Coord up = new Coord(currentCoord.i(), currentCoord.j()-1);
            if(visited.containsKey(up)){
                node.edges.add(new Edge(visited.get(up),1.0));
            }
        }
        return visited.get(new Coord(startI, startJ));
    }
    public static Map<Coord, Node> buildMazeMap(char[][] input,int startI, int startJ,  char wall){
        int rows = input.length;
        int cols = input[0].length;
        Map<Coord, Node> visited = new HashMap<>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(input[r][c] != wall){
                    Node node = new Node(c,r);
                    if(!visited.containsKey(node.coord)) {
                        visited.put(node.coord, node);
                    }
                }
            }
        }

        for(Node node : visited.values()){
            var currentCoord = node.coord;
            Coord left = new Coord(currentCoord.i() - 1, currentCoord.j());
            if(visited.containsKey(left)){
                node.edges.add(new Edge(visited.get(left),1.0));
            }

            Coord right = new Coord(currentCoord.i() + 1, currentCoord.j());
            if(visited.containsKey(right)){
                node.edges.add(new Edge(visited.get(right),1.0));
            }

            Coord bottom = new Coord(currentCoord.i(), currentCoord.j()+1);
            if(visited.containsKey(bottom)){
                node.edges.add(new Edge(visited.get(bottom),1.0));
            }

            Coord up = new Coord(currentCoord.i(), currentCoord.j()-1);
            if(visited.containsKey(up)){
                node.edges.add(new Edge(visited.get(up),1.0));
            }
        }
        return visited;
    }
}
