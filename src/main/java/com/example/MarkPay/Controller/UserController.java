
package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.User;
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
@RequestMapping(path = "/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = "/add")
  public @ResponseBody String add(@RequestBody User user) {
    String msg = "Saved!\n ";
    if (userService.findByUsername(user.getUsername()) != null) {
      User existUser = userService.findByUsername(user.getUsername());
      existUser.updateAll(user);
      userService.save(existUser);
      msg += "Existing User, info has been updated.\n";
    } else {
      userService.save(user);
    }
    return msg + user;
  }

  // localhost:8080/user/all
  @GetMapping(path = "/all")
  public @ResponseBody List<User> getAllUsers() {
    return userService.listAll();
  }

  @GetMapping(path = "/username/{username}")
  public ResponseEntity<User> get(@PathVariable String username) {
    try {
      User user = userService.findByUsername(username);
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  // Return a list of users
  // not in use since we don't allow duplicate username at this moment.
  @GetMapping(path = "/username/all/{username}")
  public ResponseEntity<List<User>> getAllUserByUsername(@PathVariable String username) {
    try {
      List<User> userList = new ArrayList<>();
      userService.findAllByUsername(username).forEach(userList::add);
      return new ResponseEntity<>(userList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/username/{username}")
  public @ResponseBody String update(@RequestBody User user, @PathVariable String username) {
    try {
      User existUser = userService.findByUsername(username);
      existUser.updateAll(user);
      userService.save(existUser);
      return "Updated!\n" + existUser;
    } catch (Exception e) {
      return "User not found!";
    }
  }

  // Delete user by username
  @DeleteMapping("/delete/username/{username}")
  public @ResponseBody String delete(@PathVariable String username) {
    userService.deleteByUsername(username);
    return "Deleted! \n";
  }
}