package com.sfeir.aoc.main10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinObjetsSacADos {

    public static long minObjets(int W, int[] poids) {
        System.out.println("W is " + W);
        Arrays.stream(poids).forEach(p -> System.out.print(p+","));
        List<Long> dp = new ArrayList<Long>();
        dp.add(Long.MAX_VALUE / 2);

        // Initialisation : dp[i] = très grand nombre
        for (long i = 1; i <= W; i++) {
            dp.add(Long.MAX_VALUE / 2); // éviter overflow
        }

        dp.set(0,0L); // 0 objets pour faire un poids de 0

        for (int i = 1; i <= W; i++) {
            for (int p : poids) {
                if (i >= p) {
                    dp.set(i, Math.min(dp.get(i), dp.get(i - p) + 1));
                }
            }
        }

        // Si W est inatteignable
        if (dp.get(W) >= Long.MAX_VALUE / 2) {
            return -1; // Impossible de remplir exactement W
        }
        System.out.println(dp.get(W));
        return dp.get(W);
    }

    public static void main(String[] args) {
        int W = 28257;
        int[] poids = {11101,1100,10001,111,11110};

        long resultat = minObjets(W, poids);

        if (resultat == -1) {
            System.out.println("Impossible de remplir exactement W.");
        } else {
            System.out.println("Nombre minimal d'objets : " + resultat);
        }
    }
}