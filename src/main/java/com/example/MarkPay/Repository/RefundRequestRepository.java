package com.example.MarkPay.Repository;

import com.example.MarkPay.Object.RefundRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RefundRequestRepository extends CrudRepository<RefundRequest, Integer>, JpaRepository<RefundRequest, Integer> {

  RefundRequest findByUsername(String username);

  void deleteByUsername(String username);
}