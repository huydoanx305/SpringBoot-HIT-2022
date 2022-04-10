package com.hit.kiemtra.services.impl;

import com.hit.kiemtra.dto.AddressDTO;
import com.hit.kiemtra.exception.InternalServerException;
import com.hit.kiemtra.exception.NotFoundException;
import com.hit.kiemtra.models.Address;
import com.hit.kiemtra.models.Darling;
import com.hit.kiemtra.repositories.AddressRepository;
import com.hit.kiemtra.repositories.DarlingRepository;
import com.hit.kiemtra.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address findAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        checkNotFoundAddress(address);
        return address.get();
    }

    @Override
    public Address createAddress(AddressDTO addressDTO, Long darlingId) {
        Address address = mapper.map(addressDTO, Address.class);
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        if(darling.isEmpty()) {
            throw new InternalServerException("Darling does not exist. Can't create address");
        }
        address.setDarling(darling.get());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(AddressDTO addressDTO, Long darlingId, Long id) {
        Optional<Address> address = addressRepository.findById(id);
        checkNotFoundAddress(address);
        Optional<Darling> darling = darlingRepository.findById(id);
        try {
            address.ifPresent(value -> mapper.map(addressDTO, value));
            darling.ifPresent(darling1 -> address.ifPresent(value -> value.setDarling(darling1)));
            return addressRepository.save(address.get());
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update address");
        }
    }

    @Override
    public void deleteAddress(Long darlingId, Long id) {
        Optional<Address> address = addressRepository.findById(id);
        checkNotFoundAddress(address);
        if(address.get().getDarling().getId() != darlingId.longValue()) {
            throw new NotFoundException("Address id " + id + " does not exist Darling id " + darlingId);
        }
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete address");
        }
    }

    @Override
    public List<Address> findAddressByDarlingId(Long darlingId) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        if(darling.get().getAddressList().isEmpty()){
            throw new NotFoundException("Not found Address with Darling Id = " + darlingId);
        }
        return darling.get().getAddressList();
    }

    private void checkNotFoundAddress(Optional<Address> address) {
        if(address.isEmpty()) {
            throw new NotFoundException("Not Found Address");
        }
    }
}
