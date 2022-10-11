package com.example.MarkPay.Repository;

import com.example.MarkPay.Object.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, Integer>, JpaRepository<CreditCard, Integer> {

  CreditCard findByUsername(String username);

  void deleteByUsername(String username);
}