package com.ddam40.chapter6;

import com.ddam40.chapter4.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Chapter6_1 {
    public static enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                groupingBy(d -> {
                    if(d.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if(d.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));
        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, Integer> totalCalroiesByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCalroiesByType);

        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu.get(true));

        Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());

    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                        .collect(partitioningBy(candidate -> isPrime(candidate)));
    }
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }
}
