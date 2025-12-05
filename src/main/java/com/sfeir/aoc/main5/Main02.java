package com.sfeir.aoc.main5;

import com.sfeir.aoc.graph.Coord;
import com.sfeir.aoc.graph.CoordL;
import com.sfeir.aoc.utils.ReadFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main02 {
    public static void main(String[] args) {

        String fileName = "src/main/resources/input_05.txt";
        var lines = ReadFile.readInputFileLines(fileName);
        boolean ranges = true;
        List<Long> ids = new ArrayList<>();
        List<CoordL> coords = new ArrayList<>();
        for(int i=0; i< lines.size(); i++){
            if(ranges){
                if(lines.get(i).trim().isEmpty()){
                    ranges = false;
                    continue;
                }
                var parts = lines.get(i).split("-");
                CoordL e = new CoordL(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                coords.add(e);
            }
            else{
                ids.add(Long.parseLong(lines.get(i)));
            }
        }

        var sortedRanges = coords.stream().sorted((c1,c2)-> Long.compare(c1.i(), c2.i())).toList();
        long maxEnd = -1;
        long result = 0l;
        for(CoordL coord : sortedRanges){
            if(maxEnd< coord.i()){
                result += Math.max(0, coord.j()+1- coord.i());
                maxEnd = coord.j();
            }
            else if(maxEnd < coord.j()){
                result += Math.max(0, coord.j()- maxEnd);
                maxEnd = coord.j();
            }
        }
        System.out.println(result);
    }



}