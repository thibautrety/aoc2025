package com.sfeir.aoc.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NodeLight {
    public String name;
    public List<EdgeLight> edges;
    public Set<NodeLight> parents;
    public long cost;
    public long costD;
    public long costF;

    boolean hasD;
    boolean hasF;

    public long costTotal;


    public NodeLight(String name){
        this.name = name;
        this.edges = new ArrayList<>();
        parents = new HashSet<>();
        cost = 1;
        costD = name.equals("dac")?1:0;
        costF = name.equals("fft")?1:0;
        costTotal = 0;
        hasD = costD>0;
        hasF = costF>0;
    }

    public void addEdge(NodeLight target){
        this.edges.add(new EdgeLight(target, 1));
    }

    public void addParent(NodeLight parent){
        this.parents.add(parent);
        hasD = hasD || parents.stream().anyMatch(p -> p.hasD);
        hasF = hasF || parents.stream().anyMatch(p -> p.hasF);
        cost = parents.stream().mapToLong( n -> n.cost).sum();
        long previous = costTotal;
//        if(name.equals("dac")){
//            hasD = true;
//            if(isHasF()){
//                costTotal = parents.stream().mapToLong(n -> n.costF).sum();
//            }
//            costD = cost;
////            costD = cost;
////            costF = parents.stream().mapToLong(n -> n.costF).sum();
////            costTotal = costF;
//        }
//        else if(name.equals("fft")){
//            hasF = true;
//            costF = cost;
//            if(isHasD()){
//                costTotal = parents.stream().mapToLong(n -> n.costD).sum();
//            }
////            costD = parents.stream().mapToLong(n -> n.costD).sum();
////            costF = cost;
////            costTotal = costD;
//        }
//        else {
//            costF = parents.stream().mapToLong(n -> n.costF).sum();
//            costD = parents.stream().mapToLong(n -> n.costD).sum();
//            costTotal = parents.stream().mapToLong(n -> n.costTotal).sum();
//        }
//        if(parent.isHasD() && isHasF()){
//
//        }


//        if(costTotal>2){
//            System.out.println(costTotal);
//        }
    }

    public long getCost(){
        if(parents.size() == 0){
            return 1;
        }
        return parents.stream().mapToLong( n -> n.getCost()).sum();

//        cost = parents.stream().mapToLong( n -> n.cost).sum();
//        return cost;
    }

    public long getCostTotal(){
        return costTotal;
    }

    public boolean isHasD(){
        if(hasD){
            return hasD;
        }
        return parents.stream().anyMatch(NodeLight::isHasD);
    }
    public boolean isHasF(){
        if(hasF){
            return hasF;
        }
        return parents.stream().anyMatch(NodeLight::isHasF);
    }

    public void setF(){
        hasF = true;
    }

    public void setD(){
        hasD = true;
    }


    public void updateCost() {
    }
}
