package com.example.MarkPay.Service;

import com.example.MarkPay.Repository.UserRepository;
import com.example.MarkPay.Object.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) {
        return userRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public User findByUsername(String username){return userRepository.findByUsername(username);}

    public List<User> findAllByUsername(String username){
        return jdbcTemplate.query("SELECT * FROM user WHERE username=?", BeanPropertyRowMapper.newInstance(User.class),username);
    }
}
