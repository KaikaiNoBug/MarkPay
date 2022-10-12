package com.example.MarkPay.Service;
import com.example.MarkPay.Object.CreditCard;
import com.example.MarkPay.Object.RefundRequest;
import com.example.MarkPay.Repository.CreditCardRepository;
import com.example.MarkPay.Repository.RefundRequestRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RefundRequestService {
  @Autowired
  private RefundRequestRepository refundRequestRepository;

  public List<RefundRequest> listAll() {
    return refundRequestRepository.findAll();
  }

  public void save(RefundRequest refundRequest) {
    refundRequestRepository.save(refundRequest);
  }

  public RefundRequest get(Integer id) {
    return refundRequestRepository.findById(id).get();
  }

  public void deleteById(Integer id) {
    refundRequestRepository.deleteById(id);
  }

  public void deleteByUsername(String username) {
    refundRequestRepository.deleteByUsername(username);
  }

  public RefundRequest findByUsername(String username) {
    return refundRequestRepository.findByUsername(username);
  }
}
