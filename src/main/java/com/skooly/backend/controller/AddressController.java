package com.skooly.backend.controller;
import com.skooly.backend.entity.Address;
import com.skooly.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
   private final AddressService addressService;
   
   @PostMapping
   public Address createAddress(@RequestBody Address address) {
	  return addressService.createAddress(address);
   }
   
   @GetMapping("/{id}")
   public Address getAddressById(@PathVariable Long id) {
	  return addressService.getAddressById(id);
   }
   
   @GetMapping
   public List<Address> getAllAddresses() {
	  return addressService.getAllAddresses();
   }
   
   @PutMapping("/{id}")
   public Address updateAddress(@PathVariable Long id,
		   @RequestBody Address address) {
	  return addressService.updateAddress(id, address);
   }
   
   @DeleteMapping("/{id}")
   public void deleteAddress(@PathVariable Long id) {
	  addressService.deleteAddress(id);
   }
}