package com.sfeir.aoc.main3;

import com.sfeir.aoc.utils.ReadFile;
import java.math.BigInteger;
import java.util.List;

public class Main02 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_03A.txt";
        List<String> lines = ReadFile.readInputFileLines(fileName);
        var result = new BigInteger("0");
        for (String line : lines) {
            BigInteger val = handleNJoltage2(line, 12);
            result = result.add(val);
        }
        System.out.println(result);
    }

    public static BigInteger handleNJoltage(String line, int n){
        var max = new BigInteger("0");
        if(n == 0){
            BigInteger currentMax = new BigInteger("0");
            for(int i=0; i<line.length(); i++){
                var first = line.charAt(i)+"";
                var current = new BigInteger(first);
                currentMax = current.compareTo(currentMax)<0 ? currentMax: current;
            }
            return currentMax;
        }
        for(int i=0; i<line.length(); i++){
            var first = line.charAt(i)+"";
            var remaining = handleNJoltage(line.substring(i+1), n-1);
            String remaingingAsString = remaining.toString();
            var current = new BigInteger(first+remaingingAsString);
            max = current.compareTo(max)<0 ? max: current;
        }
        return max;
    }
    
    public static BigInteger handleNJoltage2(String line, int n){
        var firstChar = line.substring(0, line.length()-11).chars().max().getAsInt();
        int firstIndex = getFirstIndex(line,0,11, firstChar);

        var secondChar = line.substring(firstIndex+1, line.length()-10).chars().max().getAsInt();
        int secondIndex = getFirstIndex(line,firstIndex+1, 10, secondChar);

        var char3 = line.substring(secondIndex+1, line.length()-9).chars().max().getAsInt();
        int index3 = getFirstIndex(line,secondIndex+1, 9, char3);

        var char4 = line.substring(index3+1, line.length()-8).chars().max().getAsInt();
        int index4 = getFirstIndex(line,index3+1, 8, char4);

        var char5 = line.substring(index4+1, line.length()-7).chars().max().getAsInt();
        int index5 = getFirstIndex(line,index4+1, 7, char5);

        var char6 = line.substring(index5+1, line.length()-6).chars().max().getAsInt();
        int index6 = getFirstIndex(line,index5+1, 6, char6);

        var char7 = line.substring(index6+1, line.length()-5).chars().max().getAsInt();
        int index7 = getFirstIndex(line,index6+1, 5, char7);

        var char8 = line.substring(index7+1, line.length()-4).chars().max().getAsInt();
        int index8 = getFirstIndex(line,index7+1, 4, char8);

        var char9 = line.substring(index8+1, line.length()-3).chars().max().getAsInt();
        int index9 = getFirstIndex(line,index8+1, 3, char9);

        var char10 = line.substring(index9+1, line.length()-2).chars().max().getAsInt();
        int index10 = getFirstIndex(line,index9+1, 2, char10);

        var char11 = line.substring(index10+1, line.length()-1).chars().max().getAsInt();
        int index11 = getFirstIndex(line,index10+1, 1, char11);

        var char12 = line.substring(index11+1, line.length()-0).chars().max().getAsInt();

        var result = new BigInteger(""+(char)firstChar+(char)secondChar+(char)char3+(char)char4+(char)char5+(char)char6+(char)char7+(char)char8+(char)char9+(char)char10+(char)char11+(char)char12);

//        for(int i=0; i<line.length(); i++){
//            var first = line.charAt(i)+"";
//            var remaining = handleNJoltage(line.substring(i+1), n-1);
//            String remaingingAsString = remaining.toString();
//            var current = new BigInteger(first+remaingingAsString);
//            max = current.compareTo(max)<0 ? max: current;
//        }
        return result;
    }

    private static int getFirstIndex(String line, int start, int remainingSize, int firstChar) {
        int firstIndex = 0;
        for(int i = start; i< line.length()-remainingSize; i++){
            if(line.charAt(i) == firstChar) {
                firstIndex = i;
                break;
            }
        }
        return firstIndex;
    }

}