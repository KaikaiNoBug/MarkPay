package com.example.MarkPay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreditCardRepository extends CrudRepository<CreditCard, Integer>, JpaRepository<CreditCard, Integer> {

  CreditCard findByUsername(String username);

  void deleteByUsername(String username);
}