package com.ddam40.chapter5;

import com.ddam40.chapter4.Dish;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Chapter5 {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 2, 1, 1, 3, 4, 2);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        Dish.menu.stream()
                .filter(d -> d.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .map(Dish::getName)
                .forEach(System.out::println);

        List<String> words = Arrays.asList("java8", "Lambda", "In", "Action");
        words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        List<Integer> n1 = Arrays.asList(1, 2, 3);
        List<Integer> n2 = Arrays.asList(3, 4);
        n1.stream()
                .flatMap(i -> n2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(toList())
                .forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));

        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findFirst().ifPresent(d -> System.out.println(d.getName()));
    }
}
