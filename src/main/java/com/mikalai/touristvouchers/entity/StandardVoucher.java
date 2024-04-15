package com.mikalai.touristvouchers.entity;

public class StandardVoucher extends AbstractTouristVoucher {
  private String tourDescription;

  public String getTourDescription() {
    return tourDescription;
  }

  public void setTourDescription(String tourDescription) {
    this.tourDescription = tourDescription;
  }
}
