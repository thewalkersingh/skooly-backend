package com.skooly.backend.service;
import com.skooly.backend.entity.Address;

import java.util.List;

public interface AddressService {
   Address getAddressById(Long id);
   
   List<Address> getAllAddresses();
   
   Address createAddress(Address address);
   
   Address updateAddress(Long id, Address address);
   
   void deleteAddress(Long id);
}