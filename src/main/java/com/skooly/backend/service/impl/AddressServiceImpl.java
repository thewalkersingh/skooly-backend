package com.skooly.backend.service.impl;
import com.skooly.backend.entity.Address;
import com.skooly.backend.repository.AddressRepository;
import com.skooly.backend.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
   private final AddressRepository addressRepository;
   
   @Override
   public Address getAddressById(Long id) {
	  return addressRepository.findById(id)
							  .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
   }
   
   @Override
   public List<Address> getAllAddresses() {
	  return addressRepository.findAll();
   }
   
   @Override
   public Address createAddress(Address address) {
	  return addressRepository.save(address);
   }
   
   @Override
   public Address updateAddress(Long id, Address updatedAddress) {
	  Address existing = addressRepository.findById(id)
										  .orElseThrow(() -> new EntityNotFoundException(
												  "Address not found with id: " + id));
	  
	  existing.setStreet(updatedAddress.getStreet());
	  existing.setCity(updatedAddress.getCity());
	  existing.setState(updatedAddress.getState());
	  existing.setPincode(updatedAddress.getPincode());
	  existing.setType(updatedAddress.getType());
	  
	  return addressRepository.save(existing);
   }
   
   @Override
   public void deleteAddress(Long id) {
	  if(!addressRepository.existsById(id)){
		 throw new EntityNotFoundException("Address not found with id: " + id);
	  }
	  addressRepository.deleteById(id);
   }
}