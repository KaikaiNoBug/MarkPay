package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.Transaction;
import com.example.MarkPay.Object.User;
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
  private UserService userService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody Transaction transaction) {
    String msg = "Transaction Saved! ";
    if (userService.findByUsername(transaction.getUsername()) == null) {
      User user = new User();
      user.setUsername(transaction.getUsername());
      user.setAccountType("CUSTOMER");
      userService.save(user);
      msg += "New user created!\n";
    }
    transaction.setAmount((float) Math.round(transaction.getAmount() * 100) / 100);
    transactionService.save(transaction);
    return msg;
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
      return new ResponseEntity<>(transaction, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // http://localhost:8080/transaction/username/kailin
  @GetMapping(path = "/username/{username}")
  public ResponseEntity<Transaction> get(@PathVariable String username) {
    try {
      Transaction transaction = transactionService.findByUsername(username);
      return new ResponseEntity<>(transaction, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

  // Revenue = Balance(Posted) + Receivable(Paid)
  @GetMapping(path = "/revenue")
  public ResponseEntity<Float> getTotalRevenue() {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.listAll());
      final Float sum = transactionList.stream()
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);
      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/revenue/{username}")
  public ResponseEntity<Float> getRevenueByUsername(@PathVariable String username) {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.findAllByUsername(username));
      final Float sum = transactionList.stream()
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);

      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/balance")
  public ResponseEntity<Float> getTotalBalance() {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.listAll());
      final Float sum = transactionList.stream()
          .filter(Transaction -> Transaction.getPaymentStatus().equalsIgnoreCase("posted"))
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);
      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/balance/{username}")
  public ResponseEntity<Float> getBalanceByUsername(@PathVariable String username) {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.findAllByUsername(username));
      final Float sum = transactionList.stream()
          .filter(Transaction -> Transaction.getPaymentStatus().equalsIgnoreCase("posted"))
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);
      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/receivable")
  public ResponseEntity<Float> getTotalReceivable() {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.listAll());
      final Float sum = transactionList.stream()
          .filter(Transaction -> Transaction.getPaymentStatus().equalsIgnoreCase("paid"))
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);
      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/receivable/{username}")
  public ResponseEntity<Float> getReceivableByUsername(@PathVariable String username) {
    try {
      List<Transaction> transactionList = new ArrayList<>(transactionService.findAllByUsername(username));
      final Float sum = transactionList.stream()
          .filter(Transaction -> Transaction.getPaymentStatus().equalsIgnoreCase("paid"))
          .map(Transaction::getAmount)
          .reduce(0.0f, Float::sum);
      return new ResponseEntity<>((float) Math.round(sum * 100) / 100, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/id/{id}")
  public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable Integer id) {
    Transaction existTransaction = transactionService.get(id);
    existTransaction.updateAll(transaction);
    transactionService.save(existTransaction);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
