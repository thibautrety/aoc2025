//package com.sfeir.aoc.utils;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.List;
//import java.util.OptionalInt;
//import java.util.PriorityQueue;
//import java.util.Set;
////import org.apache.commons.math3.optim.linear.SimplexSolver;
//☺
//public class Machine2 {
//    private List<Integer> finalState;
//
//    public Machine2(String lightsStr){
//        finalState = Arrays.stream(lightsStr.split(",")).map(Integer::parseInt).toList();
//
//    }
//
//    public int getW(){
//        int w = 0;
//        for(int i = 0; i<finalState.size(); i++){
//            w +=  finalState.get(i)*Math.pow(4, i);
//        }
//        return w;
//    }
//
//    public int solve2(List<Command> commands) {
//        int n = finalState.size();
//        int[] target = finalState.stream().mapToInt(Integer::intValue).toArray();
//        int[] start = new int[n];
//        // Utilise une représentation unique pour chaque état
//        HashSet<String> visited = new HashSet<>();
//        ArrayList<int[]> queue = new ArrayList<>();
//        ArrayList<Integer> steps = new ArrayList<>();
//        queue.add(start);
//        steps.add(0);
//        visited.add(Arrays.toString(start));
//
//        while (!queue.isEmpty()) {
//            int[] state = queue.remove(0);
//            int step = steps.remove(0);
//            if (Arrays.equals(state, target)) return step;
//
//            for (Command cmd : commands) {
//                int[] next = Arrays.copyOf(state, n);
//                for (int idx : cmd.moves()) {
//                    next[idx]++;
//                }
//                String key = Arrays.toString(next);
//                if (!visited.contains(key)) {
//                    visited.add(key);
//                    queue.add(next);
//                    steps.add(step + 1);
//                }
//            }
//        }
//        throw new RuntimeException("impossible");
//    }
//
//    public int solve(List<Command> commands) {
//
//        SimplexSolver s = null;
//
////        SimplexSolver solver = new SimplexSolver(finalState, commands);
//        return 0;
//    }
//
//
////    public int solve(List<Command> commands) {
////        int n = finalState.size();
////        int[] target = finalState.stream().mapToInt(Integer::intValue).toArray();
////        int[] start = new int[n];
////        Set<String> visited = new HashSet<>();
////        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost + heuristic(s.regs, target)));
////        queue.add(new State(start, 0));
////        visited.add(Arrays.toString(start));
////
////        while (!queue.isEmpty()) {
////            State curr = queue.poll();
////            if (Arrays.equals(curr.regs, target)) return curr.cost;
////
////            for (Command cmd : commands) {
////                int[] next = Arrays.copyOf(curr.regs, n);
////                for (int idx : cmd.moves()) next[idx]++;
////                String key = Arrays.toString(next);
////                if (!visited.contains(key) && isValid(next, target)) {
////                    visited.add(key);
////                    queue.add(new State(next, curr.cost + 1));
////                }
////            }
////        }
////        throw new RuntimeException("impossible");
////    }
////
////    private boolean isValid(int[] state, int[] target) {
////        for (int i = 0; i < state.length; i++) {
////            if (state[i] > target[i]) return false;
////        }
////        return true;
////    }
////
////    private int heuristic(int[] state, int[] target) {
////        int h = 0;
////        for (int i = 0; i < state.length; i++) {
////            h += target[i] - state[i];
////        }
////        return h;
////    }
////
////    private static class State {
////        int[] regs;
////        int cost;
////        State(int[] regs, int cost) {
////            this.regs = regs;
////            this.cost = cost;
////        }
////    }
//
//}
