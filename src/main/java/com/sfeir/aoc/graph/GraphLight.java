package com.sfeir.aoc.graph;

import java.util.HashMap;
import java.util.Map;

public class GraphLight {
    Map<String, NodeLight> nodes;
    public GraphLight(){
        this.nodes = new HashMap<>();
    }

    public NodeLight getNode(String name){
        return nodes.get(name);
    }

    public NodeLight addNode(NodeLight nodeLight){
        if(!nodes.containsKey(nodeLight.name)){
            nodes.put(nodeLight.name, nodeLight);
        }
        return nodeLight;
    }
}
