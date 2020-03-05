package com.ddam40.chapter8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Point {
    private final int x;
    private final int y;
    public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX).thenComparing(Point::getY);
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
        return points.stream().map(p -> new Point(p.x + x, p.y)).collect(toList());
    }

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(Point::getX).forEach(System.out::println);
    }
}
