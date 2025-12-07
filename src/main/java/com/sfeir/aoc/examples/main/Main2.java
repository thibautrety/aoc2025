package com.sfeir.aoc.examples.main;

import com.sfeir.aoc.graph.GraphBuilder;
import com.sfeir.aoc.utils.ReadFile;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
    public static void main(String[] args) {
        var test = "a   b" ;
        String[] s = test.split(" +");
        for(int i = 0 ; i< s.length; i++){
            System.out.println("s["+i+"]="+s[i]);
        }

        LinkedList<Integer> test2 = new LinkedList<>();
        test2.add(1);
        test2.add(2);
        test2.add(3);

        while(!test2.isEmpty()){
            System.out.println(test2.pollLast());
        }


    }
}
