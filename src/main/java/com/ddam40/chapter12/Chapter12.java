package com.ddam40.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class Chapter12 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 1, 24);

        System.out.println(date + ", " + LocalDate.now());
        int year = date.get(ChronoField.YEAR);
        System.out.println(LocalTime.now());

        System.out.println(LocalDateTime.now());
    }
}
