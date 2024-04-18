package com.mikalai.touristvouchers.entity;

import com.mikalai.touristvouchers.type.TransportType;
import com.mikalai.touristvouchers.type.VoucherType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class AbstractTouristVoucher {
  private String id;
  private VoucherType type;
  private String country;
  private String daysNights;
  private LocalDateTime issueDate;
  private TransportType transport;
  private BigDecimal cost;
  private String status;
  private String bookingNumber;

  // Getters
  public String getId() {
    return id;
  }

  public VoucherType getType() {
    return type;
  }

  public String getCountry() {
    return country;
  }

  public String getDaysNights() {
    return daysNights;
  }

  public LocalDateTime getIssueDate() {
    return issueDate;
  }

  public TransportType getTransport() {
    return transport;
  }

  public BigDecimal getCost() {
    return cost;
  }
  public String getStatus() {
    return status;
  }

  public String getBookingNumber() {
    return bookingNumber;
  }

  // Setters
  public void setId(String id) {
    this.id = id;
  }

  public void setType(VoucherType type) {
    this.type = type;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setDaysNights(String daysNights) {
    this.daysNights = daysNights;
  }

  public void setIssueDate(LocalDateTime issueDate) {
    this.issueDate = issueDate;
  }

  public void setTransport(TransportType transport) {
    this.transport = transport;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setBookingNumber(String bookingNumber) {
    this.bookingNumber = bookingNumber;
  }

}

