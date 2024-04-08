package com.mikalai.touristvouchers.entity;

import com.mikalai.touristvouchers.enums.TransportType;
import com.mikalai.touristvouchers.enums.VoucherType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class TouristVoucher {
  private String id;
  private VoucherType type;
  private String country;
  private String daysNights; // Format "x/y"
  private LocalDateTime issueDate;
  private TransportType transport;
  private BigDecimal cost;

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
}

