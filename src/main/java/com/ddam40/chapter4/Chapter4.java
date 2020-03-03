package com.ddam40.chapter4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Chapter4 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> {
                    System.out.println("filtering : " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping : " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCaloricDishNames);

        List<String> menuNames = new ArrayList<>();
        for(Dish d : menu) {
            menuNames.add(d.getName());
        }
        menuNames.clear();

        menu.forEach(d -> menuNames.add(d.getName()));

        menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

    }
}


