package com.sfeir.aoc.main1;

public class Locker2 {
    public int current;
    public int zero = 0;
    public Locker2(int initial){
        this.current = initial;
    }



    public void move(String instruction){
        int sign = instruction.charAt(0)=='R' ? 1 : -1;
        int delta = Integer.parseInt(instruction.substring(1));
        int previousSign = Integer.signum(current);
        current = current +sign*delta;
        int newSign = Integer.signum(current);
        if(current ==0){
            zero++;
        }
        else if(previousSign != 0 && newSign != previousSign){
            zero++;
        }

        zero = zero+Math.abs(current/100);
        current = current%100;
        current += 100;
        current = current%100;
    }

    public int getZero(){
        return zero;
    }

}
