package com.ddam40.chapter6;

import com.ddam40.chapter4.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Chapter6 {
    public static void main(String[] args) {
        long howManyDishes = Dish.menu.stream().collect(counting());
        System.out.println(howManyDishes);
        howManyDishes = Dish.menu.stream().count();
        System.out.println(howManyDishes);

        Dish.menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).ifPresent(d -> {
            System.out.println(d.getName());
        });

        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("total cal : " + totalCalories);

        totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a+ b);
        System.out.println("total cal : " + totalCalories);

        IntSummaryStatistics summaryStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);

        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);

        Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .reduce(new ArrayList<Integer>(),
                        (List<Integer> l, Integer e) -> {
                            l.add(e);
                            return l;
                        },
                        (List<Integer> l1, List<Integer> l2) -> {
                            l1.addAll(l2);
                            return l1;
                        }).forEach(System.out::println);

        int totalCal = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCal);
    }
}
