package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.Address;
import com.example.MarkPay.Object.CreditCard;
import com.example.MarkPay.Object.Transaction;
import com.example.MarkPay.Object.User;
import com.example.MarkPay.Service.AddressService;
import com.example.MarkPay.Service.CreditCardService;
import com.example.MarkPay.Service.TransactionService;
import com.example.MarkPay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @Autowired
  private CreditCardService creditCardService;

  @Autowired
  private AddressService addressService;

  @Autowired
  private UserService userService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody Transaction transaction) {
    String userName = transaction.getUsername();
    if (userService.findByUsername(userName) == null) {
      return "User not found";
    }
    transactionService.save(transaction);
    return "Saved";
  }

  // localhost:8080/transaction/all
  @GetMapping(path = "/all")
  public @ResponseBody List<Transaction> getAllTransactions() {
    return transactionService.listAll();
  }

  // http://localhost:8080/transaction/id/9
  @GetMapping("/id/{id}")
  public ResponseEntity<Transaction> get(@PathVariable Integer id) {
    try {
      Transaction transaction = transactionService.get(id);
      return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
    }
  }

  // http://localhost:8080/transaction/username/kailin
  @GetMapping(path = "/username/{username}")
  public ResponseEntity<Transaction> get(@PathVariable String username) {
    try {
      Transaction transaction = transactionService.findByUsername(username);
      return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = "/all/{username}")
  public ResponseEntity<List<Transaction>> getAllTransactionByUsername(@PathVariable String username) {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.findAllByUsername(username));
      return new ResponseEntity<>(transactionList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/balance/{username}")
  public ResponseEntity<Float> getBalanceByUsername(@PathVariable String username) {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.findAllByUsername(username));
      final Float sum = transactionList.stream().map(Transaction::getAmount).reduce(0.0f, Float::sum);
      return new ResponseEntity<>(sum, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/id/{id}")
  public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable Integer id) {
    Transaction existTransaction = transactionService.get(id);
    if (transaction.getUsername() != null) {
      existTransaction.setUsername(transaction.getUsername());
    }
    if (transaction.getTimestamp() != null) {
      existTransaction.setTimestamp(transaction.getTimestamp());
    }
    if (transaction.getAmount() != null) {
      existTransaction.setAmount(transaction.getAmount());
    }
    if (transaction.getPaymentStatus() != null) {
      existTransaction.setPaymentStatus(transaction.getPaymentStatus());
    }
    if (transaction.getDeliveryAddress() != null) {
      existTransaction.setDeliveryAddress(transaction.getDeliveryAddress());
    }
    if (transaction.getCreditCard() != null) {
      existTransaction.setCreditCard(transaction.getCreditCard());
    }
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/username/{username}")
  public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable String username) {
    Transaction existTransaction = transactionService.findByUsername(username);
    if (transaction.getUsername() != null) {
      existTransaction.setUsername(transaction.getUsername());
    }
    if (transaction.getTimestamp() != null) {
      existTransaction.setTimestamp(transaction.getTimestamp());
    }
    if (transaction.getAmount() != null) {
      existTransaction.setAmount(transaction.getAmount());
    }
    if (transaction.getPaymentStatus() != null) {
      existTransaction.setPaymentStatus(transaction.getPaymentStatus());
    }
    if (transaction.getDeliveryAddress() != null) {
      existTransaction.setDeliveryAddress(transaction.getDeliveryAddress());
    }
    if (transaction.getCreditCard() != null) {
      existTransaction.setCreditCard(transaction.getCreditCard());
    }
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/creditCard/{transactionId}/{creditCardId}")
  public ResponseEntity<Transaction> updateCreditCard(@PathVariable Integer transactionId, @PathVariable Integer creditCardId) {
    Transaction existTransaction = transactionService.get(transactionId);
    CreditCard existCreditCard = creditCardService.get(creditCardId);
    if (existTransaction == null || existCreditCard == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    existTransaction.setCreditCard(existCreditCard);
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/address/{transactionId}/{addressId}")
  public ResponseEntity<Transaction> updateAddress(@PathVariable Integer transactionId, @PathVariable Integer addressId) {
    Transaction existTransaction = transactionService.get(transactionId);
    Address existAddress = addressService.get(addressId);
    if (existTransaction == null || existAddress == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    existTransaction.setDeliveryAddress(existAddress);
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/status/{transactionId}/{status}")
  public ResponseEntity<Transaction> updateStatus(@PathVariable Integer transactionId, @PathVariable String status) {
    Transaction existTransaction = transactionService.get(transactionId);
    if (existTransaction == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    existTransaction.setPaymentStatus(status);
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete user by id
  @DeleteMapping("/delete/id/{id}")
  public @ResponseBody String delete(@PathVariable Integer id) {
    String msg = "Deleted: " + transactionService.get(id).toString();
    transactionService.deleteById(id);
    return msg;
  }

  // Delete user by username
  @DeleteMapping("/delete/username/{username}")
  public @ResponseBody String delete(@PathVariable String username) {
    String msg = "Deleted: " + transactionService.findByUsername(username);
    transactionService.deleteByUsername(username);
    return msg;
  }
}
