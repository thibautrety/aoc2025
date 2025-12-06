package com.sfeir.aoc.main6;

import com.sfeir.aoc.utils.ReadFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main02 {
    public static void main(String[] args) {
        compute2("src/main/resources/input_06.txt");
    }

    private static void compute2(String fileName) {
        var grid = ReadFile.readGridReverse(fileName);
        List<String> numbers = new ArrayList<>();
        BigInteger finalResult = new BigInteger("0");
        String currentSign = "";
        for (int j = 0; j < grid[0].length; j++) {
            String currentNumber = "";
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] != '+' && grid[i][j] != ' ' && grid[i][j] != '*') {
                    currentNumber = currentNumber + (char) grid[i][j];
                } else if (grid[i][j] == '+' || grid[i][j] == '*') {
                    currentSign = "" + (char) grid[i][j];
                }
            }
            if (currentNumber.trim().isEmpty()) {
                System.out.println(numbers);

                BigInteger intermerdiateResult = new BigInteger((currentSign.equals("+") ? "0" : "1"));
                for (String numberPart : numbers) {
                    intermerdiateResult = currentSign.equals("+") ? intermerdiateResult.add(new BigInteger(numberPart)) : intermerdiateResult.multiply(new BigInteger(numberPart));
                }
                finalResult = finalResult.add(intermerdiateResult);
                numbers.clear();
                System.out.println(intermerdiateResult + "(" + currentSign + ")");
                System.out.println("----------------");
                currentSign = "";


            } else {
                numbers.add(currentNumber);
            }
        }

        BigInteger intermerdiateResult = new BigInteger((currentSign.equals("+") ? "0" : "1"));
        for (String numberPart : numbers) {
            intermerdiateResult = currentSign.equals("+") ? intermerdiateResult.add(new BigInteger(numberPart)) : intermerdiateResult.multiply(new BigInteger(numberPart));
        }
        finalResult = finalResult.add(intermerdiateResult);
        System.out.println(numbers);
        System.out.println(intermerdiateResult + "(" + currentSign + ")");
        System.out.println("----------------");

        System.out.println(finalResult);


    }
}