package com.example.MarkPay.Controller;

import com.example.MarkPay.Object.Address;
import com.example.MarkPay.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestBody Address address) {
        addressService.save(address);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Address> getAllAddress() {
        return addressService.listAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Address> get(@PathVariable Integer id) {
        try {
            Address address = addressService.get(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable Integer id) {
        Address existAddress = addressService.get(id);
        if (address.getName() != null) {
            existAddress.setName(address.getName());
        }
        if (address.getAddressLine() != null) {
            existAddress.setAddressLine(address.getAddressLine());
        }
        if (address.getCity() != null) {
            existAddress.setCity(address.getCity());
        }
        if (address.getCountry() != null) {
            existAddress.setCountry(address.getCountry());
        }
        if (address.getZip() != null) {
            existAddress.setZip(address.getZip());
        }
        addressService.save(existAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/name/{name}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable String name) {
        Address existAddress = addressService.findByName(name);
        if (address.getName() != null) {
            existAddress.setName(address.getName());
        }
        if (address.getAddressLine() != null) {
            existAddress.setAddressLine(address.getAddressLine());
        }
        if (address.getCity() != null) {
            existAddress.setCity(address.getCity());
        }
        if (address.getCountry() != null) {
            existAddress.setCountry(address.getCountry());
        }
        if (address.getZip() != null) {
            existAddress.setZip(address.getZip());
        }
        addressService.save(existAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public @ResponseBody String delete(@PathVariable Integer id) {
        String msg = "Deleted: " + addressService.get(id).toString();
        addressService.deleteById(id);
        return msg;
    }

    @DeleteMapping("/delete/name/{name}")
    public @ResponseBody String delete(@PathVariable String name) {
        String msg = "Deleted: " + addressService.findByName(name);
        addressService.deleteByName(name);
        return msg;
    }
}
