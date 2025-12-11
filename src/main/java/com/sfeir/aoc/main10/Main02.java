//package com.sfeir.aoc.main10;
//
//import com.sfeir.aoc.utils.Command;
//import com.sfeir.aoc.utils.Machine;
//import com.sfeir.aoc.utils.Machine2;
//import com.sfeir.aoc.utils.ReadFile;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Main02 {
//    public static void main() {
//        compute("src/main/resources/input_10example.txt");
//        var startTime = System.currentTimeMillis();
//        compute("src/main/resources/input_10.txt");
//        var endTime = System.currentTimeMillis();
//        System.out.println("Execution time: " + (endTime - startTime) + " ms");
//        //X > 13129
//    }
//
//    private static void compute(String fileName) {
//        var lines = ReadFile.readInputFileLines(fileName);
//        long count = 0L;
//        int linecount = 0;
//        for (String line  : lines){
//            if (linecount < 5) {
//                linecount++;
//                continue;
//            }
//            String[] tokens = line.split(" ");
//            var machineToken = tokens[tokens.length-1];
//            Machine2 machine = new Machine2(machineToken.substring(1, machineToken.length()-1));
//
//            List<Command> availableCommands = new ArrayList<>();
//            for(int i = 1; i<tokens.length-1; i++){
//                var commandTokens = tokens[i].substring(1, tokens[i].length()-1).split(",");
//
//                Command command = new Command(Arrays.stream(commandTokens).map(Integer::parseInt).toList());
//                availableCommands.add(command);
//            }
////            long count1 = minMoves2(machine, availableCommands);
//            var startTime = System.currentTimeMillis();
//            long count1 = machine.solve(availableCommands);
//            var end = System.currentTimeMillis();
//            System.out.println("Execution time ( "+linecount+"): " + (end - startTime) + " ms");
//
//            System.out.println(count1);
//            count += count1;
//            linecount++;
//        }
//        System.out.println("Total min moves: " + count);
//    }
//
//    private static long minMoves2(Machine2 machine, List<Command> availableCommands) {
//        long count = 0L;
//        boolean found = false;
//        return MinObjetsSacADos.minObjets(machine.getW(),availableCommands.stream().mapToInt(Command::getW).toArray());
//    }
//
//}
//
//
