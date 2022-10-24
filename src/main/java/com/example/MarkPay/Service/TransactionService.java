package com.example.MarkPay.Service;

import com.example.MarkPay.Object.Transaction;
import com.example.MarkPay.Object.User;
import com.example.MarkPay.Repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  public List<Transaction> listAll() {
    return transactionRepository.findAll();
  }

  public void save(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  public Transaction get(Integer id) {
    return transactionRepository.findById(id).get();
  }

  public void deleteById(Integer id) {
    transactionRepository.deleteById(id);
  }

  public void deleteByUsername(String username) {
    transactionRepository.deleteByUsername(username);
  }

  public Transaction findByUsername(String username) {
    return transactionRepository.findByUsername(username);
  }

  public List<Transaction> findAllByUsername(String username){
    return jdbcTemplate.query("SELECT * FROM transaction WHERE username=?", BeanPropertyRowMapper.newInstance(Transaction.class),username);
  }
}
