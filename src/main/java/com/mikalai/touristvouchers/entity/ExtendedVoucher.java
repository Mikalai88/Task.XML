package com.mikalai.touristvouchers.entity;

public class ExtendedVoucher extends TouristVoucher {
  private ExtendedHotelCharacteristic hotel;

  public ExtendedVoucher() {
    super();
  }

  public ExtendedHotelCharacteristic getHotel() {
    return hotel;
  }

  public void setHotel(ExtendedHotelCharacteristic hotel) {
    this.hotel = hotel;
  }
}
