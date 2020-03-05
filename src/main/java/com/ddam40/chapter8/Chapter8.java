package com.ddam40.chapter8;

import com.ddam40.chapter4.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter8 {
    public static void main(String[] args) {
        Runnable r2 = () -> System.out.println("Hello");
        int a = 0;
        Runnable r3 = new Runnable() {
            int a = 2;
            @Override
            public void run() {
                System.out.println(a);
            }
        };
        Runnable r4 = () -> {
            int aa = 3;
            System.out.println(aa);
        };
        doSomething((Runnable) () -> {});

        List<String> dishNames = new ArrayList<>();
        for(Dish dish : Dish.menu) {
            if(dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        dishNames = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static void doSomething(Runnable r) {}
    public static void doSomething(Task r) {}
}

interface Task {
    public void execute();
}
