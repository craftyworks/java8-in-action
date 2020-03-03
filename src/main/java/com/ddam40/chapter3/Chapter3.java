package com.ddam40.chapter3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Chapter3 {
    Comparator<String> byWeight = (String a, String b) -> 1;
    Runnable run = () -> {};
    Runnable run2 = () -> System.out.println("HellO");
    ActionListener listener = (ActionEvent evt) -> {};
    Callable<String> call = () -> "Hello";

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("build.gradle.kts"))) {
            return processor.process(br);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> list = map(Arrays.asList("tom", "jerry", "int a move"), (String s) -> s.length());
        list.forEach((Integer i) -> System.out.println("i = " + i));
        Runnable r = () -> System.out.println(list);
        new Thread(r).start();

        List<String> str = Arrays.asList("a", "c", "z", "A", "B");
        str.sort((String a, String b) -> a.compareToIgnoreCase(b));
        str.sort(String::compareTo);

        Function<Integer, Banana> c = (i) -> new Banana(i);
        c = Banana::new;
    }
}
class Banana {
    Banana(int age) {}
}
@FunctionalInterface
interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}

