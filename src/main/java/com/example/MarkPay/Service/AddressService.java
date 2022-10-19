package com.example.MarkPay.Service;

import com.example.MarkPay.Repository.AddressRepository;
import com.example.MarkPay.Object.Address;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Address get(Integer id) {
        return addressRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        addressRepository.deleteByName(name);
    }

    public Address findByName(String name) {
        return addressRepository.findByName(name);
    }
}
