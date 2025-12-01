package com.sfeir.aoc.main1;

import com.sfeir.aoc.utils.ReadFile;
import java.util.List;

public class Main02 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_01A.txt";
        List<String> lines = ReadFile.readInputFileLines(fileName);
        Locker2 locker = new Locker2(50);
        for (String line : lines) {
            locker.move(line);
        }
        System.out.println(locker.getZero());

    }
}