package com.sfeir.aoc.main7;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.graph.Node;
import com.sfeir.aoc.utils.ReadFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Main02 {
    public static void main() {
        compute("src/main/resources/input_07example.txt", 0, 7);

        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_07.txt", 0, 70);
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName, int startI, int startJ) {
        var grid = ReadFile.readGridReverse(fileName);
        downStep(grid, startI, startJ);
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
            Node node = new Node(coord);

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
                } else {
                    Coord coordRight = new Coord(coord.i() + 1, coord.j() + 1);
                    queue.add(coordRight);

                    Coord coordLeft = new Coord(coord.i() + 1, coord.j() - 1);
                    queue.add(coordLeft);

                    Node nextNodeR = createChild(coordRight, graph);
                    nextNodeR.addParent(node);

                    Node nextNodeL = createChild(coordLeft, graph);
                    nextNodeL.addParent(node);
                }
            }
            if(coord.i() == grid[0].length-1){
                leaves.add(graph.get(coord));
            }
        }
        System.out.println("leaves is : ");
        System.out.println(leaves.stream().mapToLong(Node::getCost).sum());
    }

    private static Node createChild(Coord nextCoord, Map<Coord, Node> visitedNodes) {
        Node nextNode;
        if(!visitedNodes.containsKey(nextCoord)){
            nextNode = new Node(nextCoord);
            visitedNodes.put(nextCoord, nextNode);
        }
        else{
            nextNode = visitedNodes.get(nextCoord);
        }
        return nextNode;
    }
}


