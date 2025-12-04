package com.sfeir.aoc.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphBuilder {

    public static Node buildGraph(char[][] input,int startI, int startJ,  char wall){
        int rows = input.length;
        int cols = input[0].length;
        Map<String, Node> visited = new HashMap<>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(input[r][c] != wall){
                    Node node = new Node(r+";"+c);
                    if(!visited.containsKey(node.name)) {
                        visited.put(node.name, node);
                    }
                }
            }
        }

//        for(Node node : visited.values()){
//            node.
//        }
        return null;
    }
}
