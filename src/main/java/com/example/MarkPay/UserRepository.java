package com.example.MarkPay;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

}