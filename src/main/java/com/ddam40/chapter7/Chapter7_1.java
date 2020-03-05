package com.ddam40.chapter7;

import java.util.stream.IntStream;

public class Chapter7_1 {
    public final static String SENTENCE = " Nel    mezzo del cammin     di nostra  vita " +
            "mi ritrovai  in   una        selval       oscura" +
            " ch  la  dritta  via   era smarriat ";

    public static void main(String[] args) {

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Found " + countWordsCharacterStreams(SENTENCE) + " words");
    }

    public static int countWordsCharacterStreams(String s) {
        return IntStream.range(0, s.length()).mapToObj(n -> s.charAt(n)).parallel().reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine
        ).getCounter();
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if(Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
