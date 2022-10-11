package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.Transaction;
import com.example.MarkPay.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody Transaction transaction) {
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

  @PutMapping("/update/id/{id}")
  public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable Integer id) {
    Transaction existTransaction = transactionService.get(id);
    if (transaction.getUsername() != null) {
      existTransaction.setUsername(transaction.getUsername());
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