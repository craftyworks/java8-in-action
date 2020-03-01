package com.ddam40.chapter1;

public class AppleGreenColorPredicate implements ApplePredicate {
  @Override
  public boolean test(Apple apple) {
    return "green".equals(apple.getColor());
  }
}
