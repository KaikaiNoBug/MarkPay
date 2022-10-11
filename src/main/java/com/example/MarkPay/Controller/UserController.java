
package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.User;
import com.example.MarkPay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody User user) {
    userService.save(user);
    return "Saved";
  }

  // localhost:8080/user/all
  @GetMapping(path = "/all")
  public @ResponseBody List<User> getAllUsers() {
    return userService.listAll();
  }

  // http://localhost:8080/user/id/9
  @GetMapping("/id/{id}")
  public ResponseEntity<User> get(@PathVariable Integer id) {
    try {
      User user = userService.get(id);
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  // http://localhost:8080/user/username/kailin
  @GetMapping(path = "/username/{username}")
  public ResponseEntity<User> get(@PathVariable String username) {
    try {
      User user = userService.findByUsername(username);
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/update/id/{id}")
  public ResponseEntity<User> update(@RequestBody User user, @PathVariable Integer id) {
    User existUser = userService.get(id);
    if (user.getUsername() != null) {
      existUser.setUsername(user.getUsername());
    }
    if (user.getPassword() != null) {
      existUser.setPassword(user.getPassword());
    }
    if (user.getEmail() != null) {
      existUser.setEmail(user.getEmail());
    }
    if (user.getAccountType() != null) {
      existUser.setAccountType(user.getAccountType());
    }
    if (user.getAddress() != null) {
      existUser.setAddress(user.getAddress());
    }
    userService.save(existUser);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/username/{username}")
  public ResponseEntity<User> update(@RequestBody User user, @PathVariable String username) {
    User existUser = userService.findByUsername(username);
    if (user.getUsername() != null) {
      existUser.setUsername(user.getUsername());
    }
    if (user.getPassword() != null) {
      existUser.setPassword(user.getPassword());
    }
    if (user.getEmail() != null) {
      existUser.setEmail(user.getEmail());
    }
    if (user.getAccountType() != null) {
      existUser.setAccountType(user.getAccountType());
    }
    if (user.getAddress() != null) {
      existUser.setAddress(user.getAddress());
    }
    userService.save(existUser);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete user by id
  @DeleteMapping("/delete/id/{id}")
  public @ResponseBody String delete(@PathVariable Integer id) {
    String msg = "Deleted: " + userService.get(id).toString();
    userService.deleteById(id);
    return msg;
  }

  // Delete user by username
  @DeleteMapping("/delete/username/{username}")
  public @ResponseBody String delete(@PathVariable String username) {
    String msg = "Deleted: " + userService.findByUsername(username);
    userService.deleteByUsername(username);
    return msg;
  }
}