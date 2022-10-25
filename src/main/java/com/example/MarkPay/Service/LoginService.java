package com.example.MarkPay.Service;

import com.example.MarkPay.Dto.LoginRequestDTO;
import com.example.MarkPay.Object.User;
import com.example.MarkPay.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public String login(LoginRequestDTO loginRequestDTO) {
        if (userRepository.findByUsername(loginRequestDTO.getUsername())== null){
            return "Sign-in failed, username not found.";}
        User user = userRepository.findByUsername(loginRequestDTO.getUsername());
        if(!user.getPassword().equals(loginRequestDTO.getPassword())){
            return "Sign-in failed, incorrect password.";
        }
        return "Successfully signed-in!";
    }
}
