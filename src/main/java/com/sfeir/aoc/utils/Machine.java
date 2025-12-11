package com.sfeir.aoc.utils;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private List<Boolean> lights;
    private List<Boolean> finalState;
    private final List<Boolean> resetLights;

    public Machine(String lightsStr){
        finalState = lightsStr.chars().mapToObj(c -> c == '#').toList();
        lights = new ArrayList<>(lightsStr.chars().mapToObj(c -> false).toList());
        resetLights = List.copyOf(lights);
    }

    public void toggle(int index){
        lights.set(index, !lights.get(index));
    }

    public List<Boolean> getLights(){
        return lights;
    }

    public void display(){
        for(Boolean light: lights){
            System.out.print(light ? "#" : ".");
        }
        System.out.println();
    }

    public void reset(){
        lights = new ArrayList<>(resetLights);
    }

    public boolean isInFinalState() {
        return lights.equals(finalState);
    }

    public List<Integer> nearest(List<Command> availableCommands) {
        int distance = Integer.MAX_VALUE;
        List<Integer> nearest = nearest();
        List<Integer> min = new ArrayList<>();

        for(Command command: availableCommands){
            int currentDistance = 0;
            for(Integer move : command.moves()){
                if(!nearest.contains(move)){
                    currentDistance++;
                }
            }
            if(currentDistance < distance){
                distance = currentDistance;
                min = command.moves();
            }
            if(distance == 0){
                return nearest;
            }
        }
        return min;
    }

    public List<Integer> nearest() {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<lights.size(); i++){
            if(lights.get(i) != finalState.get(i)){
                result.add(i);
            }
        }
        return result;
    }

}
