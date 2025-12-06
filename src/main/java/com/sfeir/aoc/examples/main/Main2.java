package com.sfeir.aoc.examples.main;

import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.utils.ReadFile;

public class Main2 {
    public static void main(String[] args) {
        var test = "a   b" ;
        String[] s = test.split(" +");
        for(int i = 0 ; i< s.length; i++){
            System.out.println("s["+i+"]="+s[i]);
        }

    }
}
