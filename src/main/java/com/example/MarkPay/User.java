package com.example.MarkPay;

public class User {
    private String username;
    private String password;
    private String email;
    private AccountType accountType;
    private Address address;

    public User(String username, String password, String email, AccountType accountType, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.address = address;
    }

    public User(String username) {
        this.username = username;
        this.password = "N/A";
        this.email = "N/A";
    }

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", accountType="
                + accountType + ", address=" + address + "]";
    }

}
