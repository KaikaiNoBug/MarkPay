package com.example.MarkPay.Repository;

import com.example.MarkPay.Object.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>, JpaRepository<Address, Integer> {
    Address findByName(String name);

    void deleteByName(String name);
}
