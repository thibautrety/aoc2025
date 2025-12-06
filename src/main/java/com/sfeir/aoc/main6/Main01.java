package com.sfeir.aoc.main6;

import com.sfeir.aoc.graph.CoordL;
import com.sfeir.aoc.utils.ReadFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main01 {
    public static void main(String[] args) {

        compute("src/main/resources/input_06example.txt");
        compute("src/main/resources/input_06.txt");
    }

    private static void compute(String fileName) {
        var lines = ReadFile.readInputFileLines(fileName);
        Map<Integer, List<String>> elementsPerColumn = new HashMap<>();
        int max = 0;
        for (String line : lines) {
            var elements = line.trim().split(" +");

            for (int i = 0; i < elements.length; i++) {
                elementsPerColumn.putIfAbsent(i, new ArrayList<>());
                if(!elements[i].trim().isEmpty()) {
                    elementsPerColumn.get(i).add(elements[i]);
                    if (i > max) {
                        max = i;
                    }
                }
            }
        }
        var result = new BigInteger("0");
        for( int i = 0; i<=max; i++){
            var list = elementsPerColumn.get(i);
            if(list.get(list.size()-1).equals("+")){
                list.removeLast();
                result = result.add(new BigInteger(""+list.stream().mapToInt(Integer::parseInt).sum()));
            } else {
                list.removeLast();
                var mult = new BigInteger("1");
                for( String e : list){
                    mult = mult.multiply(new BigInteger(e));
                }
                result = result.add(new BigInteger(""+mult));
            }
        }
        System.out.println(result);

    }


}