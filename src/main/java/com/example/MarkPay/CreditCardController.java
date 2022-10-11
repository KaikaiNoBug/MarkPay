package com.example.MarkPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/credit-card")
public class CreditCardController {

  @Autowired
  private CreditCardService creditCardService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody CreditCard creditCard) {
    creditCardService.save(creditCard);
    return "Saved";
  }

  // localhost:8080/credit-card/all
  @GetMapping(path = "/all")
  public @ResponseBody List<CreditCard> getAllCreditCards() {
    return creditCardService.listAll();
  }

  // http://localhost:8080/credit-card/id/9
  @GetMapping("/id/{id}")
  public ResponseEntity<CreditCard> get(@PathVariable Integer id) {
    try {
      CreditCard creditCard = creditCardService.get(id);
      return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
    }
  }

  // http://localhost:8080/credit-card/username/kailin
  @GetMapping(path = "/username/{username}")
  public ResponseEntity<CreditCard> get(@PathVariable String username) {
    try {
      CreditCard creditCard = creditCardService.findByUsername(username);
      return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/update/id/{id}")
  public ResponseEntity<CreditCard> update(@RequestBody CreditCard creditCard, @PathVariable Integer id) {
    CreditCard existCreditCard = creditCardService.get(id);
    if (creditCard.getUsername() != null) {
      existCreditCard.setUsername(creditCard.getUsername());
    }
    if (creditCard.getCardHolder() != null) {
      existCreditCard.setCardHolder(creditCard.getCardHolder());
    }
    if (creditCard.getCardNumber() != null) {
      existCreditCard.setCardNumber(creditCard.getCardNumber());
    }
    if (creditCard.getExpirationMonth() != null) {
      existCreditCard.setExpirationMonth(creditCard.getExpirationMonth());
    }
    if (creditCard.getExpirationYear() != null) {
      existCreditCard.setExpirationYear(creditCard.getExpirationYear());
    }
    if (creditCard.getSecurityCode() != null) {
      existCreditCard.setSecurityCode(creditCard.getSecurityCode());
    }
    if (creditCard.getBillingAddress() != null) {
      existCreditCard.setBillingAddress(creditCard.getBillingAddress());
    }
    creditCardService.save(existCreditCard);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/username/{username}")
  public ResponseEntity<CreditCard> update(@RequestBody CreditCard creditCard, @PathVariable String username) {
    CreditCard existCreditCard = creditCardService.findByUsername(username);
    if (creditCard.getUsername() != null) {
      existCreditCard.setUsername(creditCard.getUsername());
    }
    if (creditCard.getCardHolder() != null) {
      existCreditCard.setCardHolder(creditCard.getCardHolder());
    }
    if (creditCard.getCardNumber() != null) {
      existCreditCard.setCardNumber(creditCard.getCardNumber());
    }
    if (creditCard.getExpirationMonth() != null) {
      existCreditCard.setExpirationMonth(creditCard.getExpirationMonth());
    }
    if (creditCard.getExpirationYear() != null) {
      existCreditCard.setExpirationYear(creditCard.getExpirationYear());
    }
    if (creditCard.getSecurityCode() != null) {
      existCreditCard.setSecurityCode(creditCard.getSecurityCode());
    }
    if (creditCard.getBillingAddress() != null) {
      existCreditCard.setBillingAddress(creditCard.getBillingAddress());
    }
    creditCardService.save(existCreditCard);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete user by id
  @DeleteMapping("/delete/id/{id}")
  public @ResponseBody String delete(@PathVariable Integer id) {
    String msg = "Deleted: " + creditCardService.get(id).toString();
    creditCardService.deleteById(id);
    return msg;
  }

  // Delete user by username
  @DeleteMapping("/delete/username/{username}")
  public @ResponseBody String delete(@PathVariable String username) {
    String msg = "Deleted: " + creditCardService.findByUsername(username);
    creditCardService.deleteByUsername(username);
    return msg;
  }
}