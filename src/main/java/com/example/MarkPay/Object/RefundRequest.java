package com.example.MarkPay.Object;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
public class RefundRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  private Timestamp timestamp;

  private String username;

  private String status;

  @OneToOne(cascade = CascadeType.ALL, targetEntity = Transaction.class)
  @JoinColumn(name="transaction_id")
  private Transaction transaction;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public String toString() {
    return "RefundRequest{" +
        "id=" + id +
        ", timestamp=" + timestamp +
        ", username='" + username + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
