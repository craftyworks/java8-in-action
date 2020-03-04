package com.ddam40.chapter5;

import sun.jvm.hotspot.utilities.StreamMonitor;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter5_3 {
    public static void main(String[] args) {
        Stream<String> strem = Stream.of("Java8", "Lambda", "in", "Action");
        strem.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {2,3,4,5,6,11,232};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        sum = Arrays.stream(numbers).reduce(0, (a, b) -> a+ b);
        System.out.println(sum);

        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("build.gradle.kts"), Charset.defaultCharset())) {
           uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().map(w -> 1).reduce((s, s2) -> {
              return s + s2;
           }).orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("unique: " + uniqueWords);
        System.out.println("----------------------");

        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        IntStream.rangeClosed(2, 9)
                .boxed()
                .filter(n -> n == 2)
                .flatMap(a -> IntStream.rangeClosed(2, 9).mapToObj(b -> new int[]{a, b, a * b}))
                .forEach(arr -> System.out.println(arr[0] + " * " + arr[1] + " = " + arr[2]));


        System.out.println("---------------------------");

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(arr -> arr[0])
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            int prev = 0;
            int curr = 1;
            @Override
            public int getAsInt() {
                int oldPrev = prev;
                int nextValue = this.prev + this.curr;
                this.prev = this.curr;
                this.curr = nextValue;
                return oldPrev;
            }
        }).limit(10).forEach(System.out::println);
    }
}
