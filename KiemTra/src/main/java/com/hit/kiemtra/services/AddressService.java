package com.hit.kiemtra.services;

import com.hit.kiemtra.dto.AddressDTO;
import com.hit.kiemtra.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<Address> findAllAddress();
    Address findAddressById(Long id);
    Address createAddress(AddressDTO addressDTO, Long darlingId);
    Address updateAddress(AddressDTO addressDTO, Long darlingId, Long id);
    void deleteAddress(Long darlingId, Long id);
    List<Address> findAddressByDarlingId(Long darlingId);
}
