package com.ddam40.chapter8;


import java.util.ArrayList;
import java.util.List;

public class Chapter8_1 {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(tweet -> {
            System.out.println("1>" + tweet);
        });
        f.registerObserver(tweet -> {
            System.out.println("2>" + tweet);
        });

        f.notifyObservers("Hello");
        f.notifyObservers("World");
    }
}

class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}

interface Observer {
    void notify(String tweet);
}

interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
