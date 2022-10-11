package com.example.MarkPay.Repository;

import com.example.MarkPay.Object.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>, JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    void deleteByUsername(String username);
}