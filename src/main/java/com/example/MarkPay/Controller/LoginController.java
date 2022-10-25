package com.example.MarkPay.Controller;

import com.example.MarkPay.Dto.LoginRequestDTO;
import com.example.MarkPay.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public @ResponseBody String login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.login(loginRequestDTO);
    }
}
