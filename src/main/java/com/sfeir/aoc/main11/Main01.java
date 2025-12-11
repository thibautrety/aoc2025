package com.sfeir.aoc.main11;

import com.sfeir.aoc.graph.EdgeLight;
import com.sfeir.aoc.graph.GraphLight;
import com.sfeir.aoc.graph.NodeLight;
import com.sfeir.aoc.utils.ReadFile;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main01 {
    public static void main() {
        compute("src/main/resources/input_11example.txt");
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_11.txt");
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName) {
        var lines = ReadFile.readInputFileLines(fileName);
        GraphLight graph = new GraphLight();
        for(String line : lines){
            var parts = line.split(":");
            var leftNode = parts[0].trim();
            var remaining = parts[1].trim().split(" ");
            if(graph.getNode(leftNode) == null){
                NodeLight nodeLight = new NodeLight(leftNode);
                graph.addNode(nodeLight);
            }
            var from = graph.getNode(leftNode);
            for(String s : remaining){
                if(graph.getNode(s.trim()) == null){
                    NodeLight nodeLight = new NodeLight(s.trim());
                    graph.addNode(nodeLight);
                }
                var to = graph.getNode(s.trim());
                from.addEdge(to);
            }
        }
        downStep(graph, graph.getNode("you"));


    }

    private static void downStep(GraphLight graph, NodeLight start) {
        var current = start;
        LinkedList<NodeLight> queue = new LinkedList<>();
        queue.add(current);
        Set<NodeLight> leaves = new HashSet<>();
        Set<NodeLight> handledNodes = new HashSet<>();
        while(!queue.isEmpty()){
            NodeLight node = queue.poll();
            if(handledNodes.contains(node)){
                continue;
            }
            handledNodes.add(node);

            for(EdgeLight edge : node.edges){
                edge.target.addParent(node);
                queue.add(edge.target);
            }
            if(node.name.equals("out")){
                leaves.add(node);
            }
        }
        System.out.println("leaves is : ");
        System.out.println(leaves.stream().mapToLong(NodeLight::getCost).sum());
    }


}


