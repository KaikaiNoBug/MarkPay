package com.example.MarkPay.Object;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

//  private Long orderId;


  @Column(name = "timestamp")
  @CreationTimestamp
  private Timestamp timestamp;

  private String username;

  private Float amount;

  private String paymentStatus;

  @OneToOne
  @JoinColumn(name = "delivery_address_id")
  private Address deliveryAddress;

//  public Long getOrderId() {
//    return orderId;
//  }
//
//  public void setOrderId(Long orderId) {
//    this.orderId = orderId;
//  }
  @OneToOne
  @JoinColumn(name = "creditcard_id")
  private CreditCard creditCard;

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

  public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }


  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "id=" + id +
//        ", orderId=" + orderId +
        ", timestamp=" + timestamp +
        ", username='" + username + '\'' +
        ", amount=" + amount +
        ", paymentStatus='" + paymentStatus + '\'' +
        ", deliveryAddress='" + deliveryAddress + '\'' +
        ", creditCard=" + creditCard +
        '}';
  }
}
