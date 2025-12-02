package com.sfeir.aoc.main2;

import com.sfeir.aoc.main1.Locker2;
import com.sfeir.aoc.utils.ReadFile;
import java.util.List;

public class Main01 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_02A.txt";
        List<String> lines = ReadFile.readInputFileLines(fileName);
        var input = lines.get(0).split(",");
        var result = 0l;
        for (int i=0; i<input.length;i++){
            var numbers = input[i].split("-");
            var startAsString = numbers[0];
            var endAsString = numbers[1];
            long start = Long.parseLong(startAsString);
            long end = Long.parseLong(endAsString);
            for(long current=start; current<=end; current++){
                var currentAsString = Long.toString(current);
                if(currentAsString.length()%2==1){
                    continue;
                }
                var firstHalf = currentAsString.substring(0,currentAsString.length()/2);
                var secondHalf = currentAsString.substring(currentAsString.length()/2);
                if(firstHalf.equals(secondHalf)){
                    result+=current;
                }
            }
        }
        System.out.println(result);
    }
}