package com.ddam40.chapter3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Chapter3_1 {
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", (i) -> new Orange(i));
    }

    static Fruit getFruit(String name, Integer age) {
        return map.get(name).apply(age);
    }

    public static void main(String[] args) {
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getAge());
        c = Comparator.comparing(Apple::getAge);
    }
}

class Apple implements Fruit {
    Integer age;

    public Apple(Integer age) {
    }

    public Integer getAge() {
        return age;
    }
}

class Orange implements Fruit {
    Orange(Integer age) {
    }
}
