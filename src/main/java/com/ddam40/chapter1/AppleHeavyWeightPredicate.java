package com.ddam40.chapter1;

public class AppleHeavyWeightPredicate implements ApplePredicate {
  @Override
  public boolean test(Apple apple) {
    return apple.getWeight() > 50;
  }
}
