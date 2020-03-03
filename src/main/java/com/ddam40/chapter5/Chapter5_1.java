package com.ddam40.chapter5;

import com.ddam40.chapter4.Dish;

import java.util.Arrays;
import java.util.List;

public class Chapter5_1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);

        int max = numbers.stream()
                .reduce( (a, b) -> (a < b) ? b : a)
                .orElse(0);
        System.out.println(max);

        int count = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);

    }
}
