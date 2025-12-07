package com.sfeir.aoc.main7;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.graph.Edge;
import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.graph.Node;
import com.sfeir.aoc.utils.ReadFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main02 {
    public static void main(String[] args) {

       // compute("src/main/resources/input_07example.txt", 0, 7);
        compute("src/main/resources/input_07.txt", 0, 70);
    }

    static Set<Coord> visited = new HashSet<>();
    static Map<Node, Integer> visitedPaths = new HashMap<>();
    static Set<Coord> finalNodes = new HashSet<>();
    static Map<Coord, Node> nodesPerCoord = new HashMap<>();

    private static void compute(String fileName, int startI, int startJ) {
        var grid = ReadFile.readGridReverse(fileName);
        downStep(grid, startI, startJ);
        displayResults(grid, false);
    }

    private static void displayResults(char[][] grid, boolean tree) {
        System.out.println("Total nodes: " + visited.size());
        System.out.println("Total paths: " + finalNodes.stream().mapToInt(coord -> nodesPerCoord.get(coord).getCost()).sum());
        if(tree) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (visited.contains(new Coord(i, j))) {
                        System.out.print("X");
                    } else if (visitedPaths.containsKey(new Node(i, j))) {
                        System.out.print('|');
                    } else {
                        System.out.print(grid[i][j]);
                    }
                }
                System.out.println();
            }
        }
    }

    private static void downStep(char[][] grid, int startI, int startJ) {
        Coord current = new Coord(startI, startJ);
        LinkedList<Coord> queue = new LinkedList<>();
        queue.add(current);
        Set<Node> leaves = new HashSet<>();
        Map<Coord, Node> handledNodes = new HashMap<>();
        Map<Coord, Node> graph = new HashMap<>();
        while(!queue.isEmpty()){
            Coord coord = queue.poll();
            Node node = new Node(coord.i(), coord.j());

            if(coord.j()>=grid.length || coord.j()<0){
                continue;
            }
            if(handledNodes.containsKey(coord)){
                continue;
            }
            if(graph.containsKey(coord)){
                node = graph.get(coord);
            }
            handledNodes.put(coord,node);

            if (coord.i() < grid[0].length) {
                if (grid[coord.i() + 1][coord.j()] != '^') {
                    Coord nextCoord = new Coord(coord.i() + 1, coord.j());
                    queue.add(nextCoord);

                    Node nextNode = createChild(nextCoord, graph);
                    nextNode.addParent(node);
                    node.edges.add(new Edge(nextNode,node.count));
                } else {
                    Coord coordRight = new Coord(coord.i() + 1, coord.j() + 1);
                    queue.add(coordRight);

                    Coord coordLeft = new Coord(coord.i() + 1, coord.j() - 1);
                    queue.add(coordLeft);

                    Node nextNodeR = createChild(coordRight, graph);
                    nextNodeR.addParent(node);
                    node.edges.add(new Edge(nextNodeR,node.count));

                    Node nextNodeL = createChild(coordLeft, graph);
                    nextNodeL.addParent(node);
                    node.edges.add(new Edge(nextNodeL,node.count));
                }
            }
            if(coord.i() == grid[0].length-1){
                leaves.add(graph.get(coord));
            }
        }
        System.out.println("leaves is : ");
        System.out.println(leaves.stream().mapToLong(n -> n.count).sum());




//        if (startI < grid[0].length) {
//            if (grid[startI + 1][startJ] != '^') {
//                downStep(grid, startI + 1, startJ);
//            } else {
//                visited.add(new Coord(startI+1, startJ));
//                downStep(grid, startI+1, startJ + 1);
//                downStep(grid, startI+1, startJ - 1);
//            }
//        }
//        if(startI == grid[0].length-1){
//            finalNodes.add(current);
//        }
    }

    private static Node createChild(Coord nextCoord, Map<Coord, Node> visitedNodes) {
        Node nextNode = new Node(nextCoord.i(), nextCoord.j());
        if(!visitedNodes.containsKey(nextCoord)){
            nextNode = new Node(nextCoord.i(), nextCoord.j());
            visitedNodes.put(nextCoord, nextNode);
//            nodesPerCoord.put(nextCoord, nextNode);
        }
        else{
            nextNode = visitedNodes.get(nextCoord);
        }
        return nextNode;
    }
}


