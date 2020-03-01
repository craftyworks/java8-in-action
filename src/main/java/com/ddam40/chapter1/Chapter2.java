package com.ddam40.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter2 {
  public static void main(String[] args) throws InterruptedException {
    List<Apple> inventory = new ArrayList<>();
    inventory.add(new Apple(15, "red"));
    inventory.add(new Apple(13, "blue"));
    inventory.add(new Apple(99, "green"));

    List<Apple> greenApples = inventory.stream().filter((Apple a) -> "green".equals(a.getColor()))
        .collect(Collectors.toList());
    for(Apple a : greenApples) {
      System.out.println(a);
    }

    filterApples(inventory, new AppleGreenColorPredicate());

    filterApples(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
      }
    });

    filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));

    new Thread(() -> System.out.println("Hello")).start();

    Thread.sleep(500);

    System.out.println("END");
  }

  public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p)  {
    List<Apple> result = new ArrayList<>();
    for(Apple apple : inventory) {
      if(p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }
}
