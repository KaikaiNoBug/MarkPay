package com.example.MarkPay;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  private Long orderId;

  private Timestamp timestamp;

  private String username;

  private Float amount;

  private String paymentStatus;

  private String deliveryAddress;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  @ManyToOne
  @JoinColumn(name = "creditcard_id")
  private CreditCard creditcard;

  public CreditCard getCreditcard() {
    return creditcard;
  }

  public void setCreditcard(CreditCard creditcard) {
    this.creditcard = creditcard;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
