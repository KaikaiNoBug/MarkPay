package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.CreditCard;
import com.example.MarkPay.Object.RefundRequest;
import com.example.MarkPay.Service.RefundRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/refund-request")
public class RefundRequestController {

  @Autowired
  private RefundRequestService refundRequestService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody RefundRequest refundRequest) {
    refundRequestService.save(refundRequest);
    return "Saved";
  }

  // localhost:8080/refund-request/all
  @GetMapping(path = "/all")
  public @ResponseBody List<RefundRequest> getAllCreditCards() {
    return refundRequestService.listAll();
  }

  // http://localhost:8080/refund-request/id/9
  @GetMapping("/id/{id}")
  public ResponseEntity<RefundRequest> get(@PathVariable Integer id) {
    try {
      RefundRequest refundRequest = refundRequestService.get(id);
      return new ResponseEntity<RefundRequest>(refundRequest, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<RefundRequest>(HttpStatus.NOT_FOUND);
    }
  }

  // http://localhost:8080/refund-request/username/kailin
  @GetMapping(path = "/username/{username}")
  public ResponseEntity<RefundRequest> get(@PathVariable String username) {
    try {
      RefundRequest refundRequest = refundRequestService.findByUsername(username);
      return new ResponseEntity<RefundRequest>(refundRequest, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<RefundRequest>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/update/id/{id}")
  public ResponseEntity<CreditCard> update(@RequestBody RefundRequest refundRequest, @PathVariable Integer id) {
    RefundRequest existRefundRequest = refundRequestService.get(id);
    if (refundRequest.getUsername() != null) {
      existRefundRequest.setUsername(refundRequest.getUsername());
    }
    if (refundRequest.getStatus() != null) {
      existRefundRequest.setStatus(refundRequest.getStatus());
    }
    if (refundRequest.getOrderId() != null) {
      existRefundRequest.setOrderId(refundRequest.getOrderId());
    }
    if (refundRequest.getTimestamp() != null) {
      existRefundRequest.setTimestamp(refundRequest.getTimestamp());
    }
    refundRequestService.save(existRefundRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/username/{username}")
  public ResponseEntity<CreditCard> update(@RequestBody RefundRequest refundRequest, @PathVariable String username) {
    RefundRequest existRefundRequest = refundRequestService.findByUsername(username);
    if (refundRequest.getUsername() != null) {
      existRefundRequest.setUsername(refundRequest.getUsername());
    }
    if (refundRequest.getStatus() != null) {
      existRefundRequest.setStatus(refundRequest.getStatus());
    }
    if (refundRequest.getOrderId() != null) {
      existRefundRequest.setOrderId(refundRequest.getOrderId());
    }
    if (refundRequest.getTimestamp() != null) {
      existRefundRequest.setTimestamp(refundRequest.getTimestamp());
    }
    refundRequestService.save(existRefundRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete user by id
  @DeleteMapping("/delete/id/{id}")
  public @ResponseBody String delete(@PathVariable Integer id) {
    String msg = "Deleted: " + refundRequestService.get(id).toString();
    refundRequestService.deleteById(id);
    return msg;
  }

  // Delete user by username
  @DeleteMapping("/delete/username/{username}")
  public @ResponseBody String delete(@PathVariable String username) {
    String msg = "Deleted: " + refundRequestService.findByUsername(username);
    refundRequestService.deleteByUsername(username);
    return msg;
  }
}
