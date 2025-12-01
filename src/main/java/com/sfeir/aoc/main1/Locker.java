package com.sfeir.aoc.main1;

public class Locker {
    public int current;
    public int zero = 0;
    public Locker(int initial){
        this.current = initial;
    }

    public void move(String instruction){
        int sign = instruction.charAt(0)=='R' ? 1 : -1;
        int delta = Integer.parseInt(instruction.substring(1));
        current = current +sign*delta;
        current = current%100;
        current += 100;
        current = current%100;
        if(current ==0){
            zero++;
        }
    }

    public int getZero(){
        return zero;
    }

}
