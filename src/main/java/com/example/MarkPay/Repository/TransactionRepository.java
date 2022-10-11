package com.example.MarkPay.Repository;

import com.example.MarkPay.Object.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>, JpaRepository<Transaction, Integer> {

  Transaction findByUsername(String username);

  void deleteByUsername(String username);
}