package com.sfeir.aoc.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {

    public static List<String> readInputFileLines(String fileName) {
        try{
            return Files.readAllLines(Path.of(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}
