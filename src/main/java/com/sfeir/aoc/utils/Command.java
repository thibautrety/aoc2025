package com.sfeir.aoc.utils;

import java.util.List;

public record Command(List<Integer> moves) {
    public void push(Machine machine){
        moves.forEach(machine::toggle);
    }

    public void display() {
        System.out.println("Command{" +
            "moves=" + moves +
            '}');
    }

    public boolean canApply(List<Integer> nearest) {
        for (Integer move : moves) {
            if (!nearest.contains(move)) {
                return false;
            }
        }
        return true;
    }

    public int getW(){
        int w = 0;
        for(int i = 0; i<moves.size(); i++){
            w += Math.pow(4, moves.get(i));
        }
        return w;
    }

}
