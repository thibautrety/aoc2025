package com.sfeir.aoc.main11;

import com.sfeir.aoc.graph.EdgeLight;
import com.sfeir.aoc.graph.GraphLight;
import com.sfeir.aoc.graph.NodeLight;
import com.sfeir.aoc.utils.ReadFile;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main02 {
    public static void main() {
       // compute("src/main/resources/input_11Bexample.txt");
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_11.txt");
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName) {
        var lines = ReadFile.readInputFileLines(fileName);
        GraphLight graph0 = buildGraph(lines, "fft");
        GraphLight graph1 = buildGraph(lines, "dac");
        GraphLight graph2 = buildGraph(lines);
        long cost1 = downStepStartEnd(graph0, graph0.getNode("svr"), graph0.getNode("fft"));
        System.out.println("cost 1 = " + cost1);
        long cost2 = downStepStartEnd(graph1, graph1.getNode("fft"), graph1.getNode("dac"));
        System.out.println("cost 2 = " + cost2);
        long cost3 = downStepStartEnd(graph2, graph2.getNode("dac"), graph2.getNode("out"));
        System.out.println("cost 3 = " + cost3);
        System.out.println("Total cost = " + (cost1 * cost2 * cost3));
//        System.out.println(downStepStartEnd(graph, graph.getNode("dac"), graph.getNode("fft")));
//        downStepMarkDac(graph, graph.getNode("dac"));
//        downStepMarkFFT(graph, graph.getNode("fft"));
//        downStep(graph, graph.getNode("svr"));


    }

    private static GraphLight buildGraph(List<String> lines, String leaf) {
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
            if(leftNode.equals(leaf)){
                continue;
            }
            for(String s : remaining){
                if(graph.getNode(s.trim()) == null){
                    NodeLight nodeLight = new NodeLight(s.trim());
                    graph.addNode(nodeLight);
                }
                var to = graph.getNode(s.trim());
                from.addEdge(to);
            }
        }
        return graph;
    }

    private static GraphLight buildGraph(List<String> lines) {
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
        return graph;
    }

    private static long downStepStartEnd(GraphLight graph, NodeLight start, NodeLight end) {
        var current = start;
        LinkedList<NodeLight> queue = new LinkedList<>();
        queue.add(current);
        Set<NodeLight> handledNodes = new HashSet<>();
        while(!queue.isEmpty()){
            NodeLight node = queue.poll();
            if(handledNodes.contains(node)){
                node.updateCost();
                continue;
            }
            handledNodes.add(node);

            for(EdgeLight edge : node.edges){
                edge.target.addParent(node);
                queue.add(edge.target);
            }
        }
//        System.out.println(leaves.stream().mapToLong(NodeLight::getCostTotal).sum());
        return end.getCost();
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
        System.out.println(leaves.stream().mapToLong(NodeLight::getCostTotal).sum());
    }

    private static void downStepMarkFFT(GraphLight graph, NodeLight start) {
        var current = start;
        LinkedList<NodeLight> queue = new LinkedList<>();
        queue.add(current);
        Set<NodeLight> leaves = new HashSet<>();
        Set<NodeLight> handledNodes = new HashSet<>();
        while(!queue.isEmpty()){
            NodeLight node = queue.poll();
            node.setF();
            if(handledNodes.contains(node)){
                continue;
            }
            for(EdgeLight edge : node.edges){
                edge.target.addParent(node);
                queue.add(edge.target);
            }

            if(node.name.equals("dac")){
                System.out.println("dac !Found cost to dac is : "+node.getCostTotal());
            }

        }
    }
    private static void downStepMarkDac(GraphLight graph, NodeLight start) {
        var current = start;
        LinkedList<NodeLight> queue = new LinkedList<>();
        queue.add(current);
        Set<NodeLight> leaves = new HashSet<>();
        Set<NodeLight> handledNodes = new HashSet<>();
        while(!queue.isEmpty()){
            NodeLight node = queue.poll();
            node.setD();
            if(handledNodes.contains(node)){
                continue;
            }
            handledNodes.add(node);
            if(node.name.equals("fft")){
                System.out.println("fft !Found cost to dac is : "+node.getCostTotal());
            }

        }
    }
}


