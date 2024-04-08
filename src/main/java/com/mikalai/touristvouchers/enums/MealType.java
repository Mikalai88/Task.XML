package com.mikalai.touristvouchers.enums;

public enum MealType {
  HB,
  BB,
  AL;

  public static MealType fromString(String text) {
    for (MealType b : MealType.values()) {
      if (b.name().equalsIgnoreCase(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("No constant with text " + text + " found");
  }
}

