package com.ddam40.chapter1;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public class Chapter1 {
  public static void main(String[] args) {
    List<Apple> inventory = new ArrayList<>();
    inventory.add(new Apple(15, "red"));
    inventory.add(new Apple(13, "blue"));
    inventory.add(new Apple(99, "green"));

    Collections.sort(inventory, new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
      }
    });
    inventory.sort(Comparator.comparing(Apple::getWeight));

    File[] hiddenFiles = new File("/").listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.isHidden();
      }
    });

    File[] hiddenFiles2 = new File("/").listFiles((File file) -> file.isHidden());

    filterApple(inventory, (Apple a) ->  "green".equals(a.getColor()));
  }

  static List<Apple> filterGreeApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for(Apple apple : inventory) {
      if("green".equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }

  static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> predicate) {
    List<Apple> result = new ArrayList<>();
    for(Apple apple : inventory) {
      if(predicate.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  static boolean isGreenApple(Apple apple) {
    return "green".equals(apple.getColor());
  }
}

interface Predicate<T> {
  boolean test(T t);
}
