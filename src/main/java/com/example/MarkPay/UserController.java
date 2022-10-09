
package com.example.MarkPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/userapi")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewUser(
      @RequestParam String username,
      @RequestParam String password,
      @RequestParam String email) {
    User n = new User();
    n.setUsername(username);
    n.setPassword(password);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/byId")
  public @ResponseBody Optional<User> getUsersById(@RequestParam Integer id) {
    return userRepository.findById(id);
  }

  @GetMapping(path = "/byUsername")
  public @ResponseBody Optional<User> getUsersByName(@RequestParam String username) {
    return Optional.ofNullable(userRepository.findByUsername(username));
  }

  @GetMapping(path = "/byEmail")
  public @ResponseBody Optional<User> getUsersByEmail(@RequestParam String email) {
    return Optional.ofNullable(userRepository.findByEmail(email));
  }
}