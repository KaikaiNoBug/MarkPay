
package com.example.MarkPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

//  localhost:8080/user/all
  @GetMapping(path = "/all")
  public @ResponseBody List<User> getAllUsers() {
    return userService.listAll();
  }


  //  localhost:8080/user/1
  @GetMapping("/{id}")
  public ResponseEntity<User> get(@PathVariable Integer id) {
    try {
      User user = userService.get(id);
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
    try {
      User existUser = userService.get(id);
      existUser.setUsername(user.getUsername());
      existUser.setPassword(user.getPassword());
      existUser.setEmail(user.getEmail());
      existUser.setAccountType(user.getAccountType());
      existUser.setAddress(user.getAddress());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

//  @GetMapping(path = "/byUsername")
//  public @ResponseBody Optional<User> getUsersByName(@RequestParam String username) {
//    return Optional.ofNullable(userService.findByUsername(username));
//  }
//
//  @GetMapping(path = "/byEmail")
//  public @ResponseBody Optional<User> getUsersByEmail(@RequestParam String email) {
//    return Optional.ofNullable(userRepository.findByEmail(email));
//  }
}