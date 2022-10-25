package com.example.MarkPay.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String username;
    private String password;
    private String email;
    private String accountType;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = CreditCard.class)
    @JoinColumn(name="creditcard_id")
    private CreditCard creditCard;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void updateAll(User newUser){
        if (newUser.getUsername() != null) {
            this.setUsername(newUser.getUsername());
        }
        if (newUser.getPassword() != null) {
            this.setPassword(newUser.getPassword());
        }
        if (newUser.getEmail() != null) {
            this.setEmail(newUser.getEmail());
        }
        if (newUser.getAccountType() != null) {
            this.setAccountType(newUser.getAccountType());
        }
        if (newUser.getAddress() != null) {
            this.setAddress(newUser.getAddress());
        }
        if (newUser.getCreditCard() != null) {
            this.setCreditCard(newUser.getCreditCard());
        }
    }
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", accountType="
                + accountType + ", address=" + address + "creditCard=" + creditCard + "]";
    }
}
