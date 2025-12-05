package com.sfeir.aoc.main5;

import com.sfeir.aoc.graph.CoordL;
import com.sfeir.aoc.utils.ReadFile;
import java.util.ArrayList;
import java.util.List;

public class Main01 {
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
                coords.add(new CoordL(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            }
            else{
                ids.add(Long.parseLong(lines.get(i)));
            }
        }

        var sortedRanges = coords.stream().sorted((c1,c2)-> Long.compare(c1.i(), c2.i())).toList();
        List<Long> result = new ArrayList<>();
        for(Long id : ids){
            boolean found = false;
            for(CoordL range : sortedRanges){
                if(id >= range.i() && id <= range.j()){
                    found = true;
                    break;
                }
            }
            if(found){
                result.add(id);
            }

        }
        System.out.println(result.size());
    }



}