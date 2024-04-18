package com.mikalai.touristvouchers.parser;

public enum VoucherXmlTag {
  STANDARD_VOUCHER("standardVoucher"),
  EXTENDED_VOUCHER("extendedVoucher"),
  HOTEL("hotel"),
  ID("id"),
  STATUS("status"),
  BOOKING_NUMBER("bookingNumber"),
  TYPE("type"),
  COUNTRY("country"),
  DAYS_NIGHTS("daysNights"),
  ISSUE_DATE("issueDate"),
  TRANSPORT("transport"),
  COST("cost"),
  TOUR_DESCRIPTION("tourDescription"),
  STARS("stars"),
  MEAL_INCLUDED("mealIncluded"),
  MEAL_TYPE("mealType"),
  ROOM_TYPE("roomType"),
  TV_INCLUDED("tvIncluded"),
  AC_INCLUDED("acIncluded"),
  WIFI_INCLUDED("wifiIncluded");

  private final String value;

  VoucherXmlTag(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static VoucherXmlTag fromString(String text) {
    for (VoucherXmlTag b : VoucherXmlTag.values()) {
      if (b.value.equalsIgnoreCase(text)) {
        return b;
      }
    }
    return null;
  }
}
