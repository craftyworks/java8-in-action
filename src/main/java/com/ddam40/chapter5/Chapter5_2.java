package com.ddam40.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter5_2 {

    static List<Transaction> transactions = null;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void main(String[] args) {
        // 1
        transactions.stream()
                .filter(tr -> tr.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        // 2
        transactions.stream()
                .map(tr -> tr.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println("--------------");
        // 3
        transactions.stream()
                .filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
                .map(tr -> tr.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);

        System.out.println("--------------");
        transactions.stream()
                .map(tr -> tr.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .reduce( (a, b) -> a + b + ", ")
                .ifPresent(System.out::println);

        System.out.println("--------------");
        if(transactions.stream()
                .anyMatch(tr -> tr.getTrader().getCity().equals("Milan"))) {
            System.out.println("YES");
        }
        System.out.println("--------------");
        transactions.stream()
                .filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
                .forEach(tr -> System.out.println(tr.getValue()));

        System.out.println("-------------------");
        transactions.stream()
                .map(tr -> tr.getValue())
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();
        IntStream.range(0, 10).forEach(System.out::println);

        System.out.println("-------------------------");

        Stream<int[]> pys = IntStream.range(1, 100)
                .boxed()
                .flatMap(a ->IntStream.range(a, 100)
                                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                .mapToObj(b ->new int[]{a, b, (int)Math.sqrt(a*a + b*b)}));
        
    }
}
