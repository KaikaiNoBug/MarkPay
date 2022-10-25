package com.example.MarkPay.Controller;

import com.example.MarkPay.Dto.SignUpRequestDTO;
import com.example.MarkPay.Object.User;
import com.example.MarkPay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/signup")
public class SignUpController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public @ResponseBody String signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        if (userService.findByUsername(signUpRequestDTO.getUsername()) != null) {
            return "User already exist!";
        }
        User user = new User();
        user.setUsername(signUpRequestDTO.getUsername());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setAccountType("CUSTOMER");
        user.setEmail(signUpRequestDTO.getEmail());
        user.setAddress(signUpRequestDTO.getAddress());
        user.setCreditCard(signUpRequestDTO.getCreditCard());
        userService.save(user);
        return "Successfully signed-up!";
    }
}
