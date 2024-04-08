package com.mikalai.touristvouchers.entity;

import com.mikalai.touristvouchers.enums.MealType;

public class HotelCharacteristic {
  private int stars;
  private boolean mealIncluded;
  private MealType mealType; // Default value is set in the constructor
  private String roomType;
  private boolean tvIncluded;
  private boolean acIncluded;

  // Default Constructor
  public HotelCharacteristic() {
    // Setting default value for mealType as per XSD
    this.mealType = MealType.BB;
  }

  // Getters
  public int getStars() {
    return stars;
  }

  public boolean isMealIncluded() {
    return mealIncluded;
  }

  public MealType getMealType() {
    return mealType;
  }

  public String getRoomType() {
    return roomType;
  }

  public boolean isTvIncluded() {
    return tvIncluded;
  }

  public boolean isAcIncluded() {
    return acIncluded;
  }

  // Setters
  public void setStars(int stars) {
    this.stars = stars;
  }

  public void setMealIncluded(boolean mealIncluded) {
    this.mealIncluded = mealIncluded;
  }

  public void setMealType(MealType mealType) {
    this.mealType = mealType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public void setTvIncluded(boolean tvIncluded) {
    this.tvIncluded = tvIncluded;
  }

  public void setAcIncluded(boolean acIncluded) {
    this.acIncluded = acIncluded;
  }
}

