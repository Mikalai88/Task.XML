package com.mikalai.touristvouchers.entity;

public class ExtendedHotelCharacteristic extends HotelCharacteristic {
  private boolean wifiIncluded;

  // Getter
  public boolean isWifiIncluded() {
    return wifiIncluded;
  }

  // Setter
  public void setWifiIncluded(boolean wifiIncluded) {
    this.wifiIncluded = wifiIncluded;
  }
}

