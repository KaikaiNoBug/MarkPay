package com.example.MarkPay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreditCardService {
  @Autowired
  private CreditCardRepository creditCardRepository;

  public List<CreditCard> listAll() {
    return creditCardRepository.findAll();
  }

  public void save(CreditCard creditCard) {
    creditCardRepository.save(creditCard);
  }

  public CreditCard get(Integer id) {
    return creditCardRepository.findById(id).get();
  }

  public void deleteById(Integer id) {
    creditCardRepository.deleteById(id);
  }

  public void deleteByUsername(String username) {
    creditCardRepository.deleteByUsername(username);
  }

  public CreditCard findByUsername(String username) {
    return creditCardRepository.findByUsername(username);
  }
}