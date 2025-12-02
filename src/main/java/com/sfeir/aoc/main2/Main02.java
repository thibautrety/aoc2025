package com.sfeir.aoc.main2;

import com.sfeir.aoc.utils.ReadFile;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main02 {
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
                int divider = 2;
                int digitsCount = currentAsString.length() / divider;
                while(divider<=currentAsString.length()){
                    if(currentAsString.length()%divider !=0){
                        divider ++;
                        digitsCount = currentAsString.length() / divider;
                        continue;
                    }
                    Set<String> elements = new HashSet<>();
                    for(int j=0; j<=currentAsString.length()-digitsCount;j+=digitsCount){
                        var element = currentAsString.substring(j, j+digitsCount);
                        elements.add(element);

                    }
                    if(elements.size() == 1){
                        System.out.println(current);
                        result+=current;
                        break;
                    }
                    divider ++;
                    digitsCount = currentAsString.length() / divider;
                }
            }
        }
        System.out.println("---------------");
        System.out.println(result);
    }
}