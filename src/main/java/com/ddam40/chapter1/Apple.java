package com.ddam40.chapter1;

public class Apple {
  private String color;
  private Integer weight;
  Apple(Integer weight, String color) {
    this.weight = weight;
    this.color = color;
  }
  public Integer getWeight() {
    return weight;
  }
  public String getColor() {return color;}
  public static boolean isGreenApple(Apple apple) {
    return "green".equals(apple.getColor());
  }
}
