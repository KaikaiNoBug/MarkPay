
package com.example.MarkPay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(@RequestParam(value = "username", defaultValue = "None") String username) {
        return new User(username).toString();
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
      return repository.save(newUser);
    }

}