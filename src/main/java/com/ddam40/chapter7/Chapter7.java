package com.ddam40.chapter7;

import org.apache.commons.lang3.time.StopWatch;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Chapter7 {
    public static void main(String[] args) {
/*
        StopWatch st = new StopWatch();
        st.start();
        System.out.println("sum : " + sequentialSum(100000000L));
        st.stop();
        System.out.println(st.toString());
        st.reset();
        st.start();
        System.out.println("sum : " + iterativeSum(100000000L));
        st.stop();
        System.out.println(st.toString());
        st.reset();
        st.start();
        System.out.println("sum : " + parallelSum(100000000L));
        st.stop();
        System.out.println(st.toString());
*/
        System.out.println(Runtime.getRuntime().availableProcessors() + " cpu");

        measureSumPerf(Chapter7::sequentialSum, 10_000_000);
        measureSumPerf(Chapter7::iterativeSum, 10_000_000);
        measureSumPerf(Chapter7::rangedSum, 10_000_000);
        measureSumPerf(Chapter7::parallelRangedSum, 10_000_000);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i<10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result : " + sum);
            if(duration < fastest) fastest = duration;
        }
        System.out.println(fastest + " msecs");
        return fastest;
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .parallel()
                    .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for(long i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }
}
