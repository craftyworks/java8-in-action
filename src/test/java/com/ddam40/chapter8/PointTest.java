package com.ddam40.chapter8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PointTest {
    @Test
    public void testMoveRightBy() throws Exception {
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);

        assertThat(p2.getX()).isEqualTo(15);
        assertThat(p2.getY()).isEqualTo(5);
    }

    @Test
    public void testComparingTwoPoints() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);

        int result = Point.compareByXAndThenY.compare(p1, p2);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void testMoveAllPointsRightBy() {
        List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expected = Arrays.asList(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertThat(newPoints).isEqualTo(expected);
    }
}
