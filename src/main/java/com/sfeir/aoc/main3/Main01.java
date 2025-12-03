package com.sfeir.aoc.main3;

import com.sfeir.aoc.utils.ReadFile;
import java.util.List;

public class Main01 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_03A.txt";
        List<String> lines = ReadFile.readInputFileLines(fileName);
        System.out.println(lines.stream().mapToLong(Main01::handleJolatage).sum());
    }

    public static Long handleJolatage(String line){
        var max = 0L;
        for(int i=0; i<line.length(); i++){
            for(int j = i+1; j<line.length(); j++){
                var first = line.charAt(i)+"";
                var second = line.charAt(j)+"";
                var current = Long.parseLong(first+second);
                max = Math.max(max,current);
            }
        }
//        System.out.println(max);
        return max;
    }
}