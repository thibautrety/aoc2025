package com.sfeir.aoc.main10;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.utils.Command;
import com.sfeir.aoc.utils.Machine;
import com.sfeir.aoc.utils.ReadFile;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main01 {
    public static void main() {
        compute("src/main/resources/input_10example.txt");
        var startTime = System.currentTimeMillis();
        compute("src/main/resources/input_10.txt");
        var endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void compute(String fileName) {
        var lines = ReadFile.readInputFileLines(fileName);
        long count = 0L;
        for (String line  : lines){
            String[] tokens = line.split(" ");
            var machineToken = tokens[0];
            Machine machine = new Machine(machineToken.substring(1, machineToken.length()-1));

            List<Command> availableCommands = new ArrayList<>();
            for(int i = 1; i<tokens.length-1; i++){
                var commandTokens = tokens[i].substring(1, tokens[i].length()-1).split(",");

                Command command = new Command(Arrays.stream(commandTokens).map(Integer::parseInt).toList());
                availableCommands.add(command);
            }
            count += minMoves2(machine, availableCommands);
        }
        System.out.println("Total min moves: " + count);
    }

    private static long minMoves(Machine machine, List<Command> availableCommands) {
        long count = 0L;
        boolean found = false;

        while(! found){
            List<Integer>  nearest = machine.nearest(availableCommands);
            for(Command command : availableCommands){
                if(command.canApply(nearest)){
                    count++;
                    command.push(machine);
                    if(machine.isInFinalState()){
                        found = true;
                        break;
                    }
                }
            }
        }
        return count;
    }
    private static long minMoves2(Machine machine, List<Command> availableCommands) {
        long count = 0L;
        boolean found = false;
        long min = Long.MAX_VALUE;
        for(int i = 0 ; i < Math.pow(2,availableCommands.size()); i++){
            count=0L;
            for(int j = 0; j<availableCommands.size();j++){
                if(((i & (int)Math.pow(2,j)))>0){
                    availableCommands.get(j).push(machine);
                    count++;
                }
            }
            if(machine.isInFinalState()){
                min = Math.min(min, count);
            }
            machine.reset();
        }
        return min;
    }

}


