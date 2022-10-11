package com.example.MarkPay;

import javax.persistence.*;

@Entity
public class CreditCard {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  private String username;

  private String cardHolder;

  private Long cardNumber;

  private Integer expirationMonth;

  private Integer expirationYear;

  private Integer securityCode;

  private String billingAddress;

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public Long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Integer getExpirationMonth() {
    return expirationMonth;
  }

  public void setExpirationMonth(Integer expirationMonth) {
    this.expirationMonth = expirationMonth;
  }

  public Integer getExpirationYear() {
    return expirationYear;
  }

  public void setExpirationYear(Integer expirationYear) {
    this.expirationYear = expirationYear;
  }

  public Integer getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(Integer securityCode) {
    this.securityCode = securityCode;
  }

  public String getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(String billingAddress) {
    this.billingAddress = billingAddress;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Creditcard{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", cardHolder='" + cardHolder + '\'' +
        ", cardNumber=" + cardNumber +
        ", expirationMonth=" + expirationMonth +
        ", expirationYear=" + expirationYear +
        ", securityCode=" + securityCode +
        ", billingAddress='" + billingAddress + '\'' +
        '}';
  }
}
