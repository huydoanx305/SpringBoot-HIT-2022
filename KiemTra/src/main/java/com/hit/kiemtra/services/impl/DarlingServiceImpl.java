package com.hit.kiemtra.services.impl;

import com.hit.kiemtra.dto.DarlingDTO;
import com.hit.kiemtra.exception.InternalServerException;
import com.hit.kiemtra.exception.NotFoundException;
import com.hit.kiemtra.models.Address;
import com.hit.kiemtra.models.Darling;
import com.hit.kiemtra.repositories.AddressRepository;
import com.hit.kiemtra.repositories.DarlingRepository;
import com.hit.kiemtra.services.DarlingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DarlingServiceImpl implements DarlingService {
    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Darling> findAllDarling() {
        return darlingRepository.findAll();
    }

    @Override
    public List<Darling> findAllDarlingByStatusOneTwo() {
        return darlingRepository.findAllByStatusIsBetween(1, 2);
    }

    @Override
    public List<Darling> findAllDarlingByStatusTwo(Integer status) {
        return darlingRepository.findAllByStatus(status);
    }

    @Override
    public Darling findDarlingById(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkNotFoundDarling(darling);
        return darling.get();
    }

    @Override
    public Darling createDarling(DarlingDTO darlingDTO) {
        try {
            Darling darling = mapper.map(darlingDTO, Darling.class);
            return darlingRepository.save(darling);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't create Darling");
        }
    }

    @Override
    public Darling updateDarling(DarlingDTO darlingDTO, Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkNotFoundDarling(darling);
        try {
            mapper.map(darlingDTO, darling.get());
            return darlingRepository.save(darling.get());
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update Darling");
        }
    }

    @Override
    public void deleteDarling(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkNotFoundDarling(darling);
        try {
            darling.get().setStatus(3);
            darlingRepository.save(darling.get());
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete Darling");
        }
    }

    @Override
    public List<Darling> findDarlingByAddress(String name) {
        LinkedList<Darling> darlings = new LinkedList<>();
        List<Address> addressList = addressRepository.findAll();
        String pattern = ".*" + name.toLowerCase() + ".*";
        for(Address address : addressList) {
            if(address.getName().toLowerCase().matches(pattern)){
                darlings.add(address.getDarling());
            }
        }
        return darlings;
    }

    private void checkNotFoundDarling(Optional<Darling> darling) {
        if(darling.isEmpty()) {
            throw new NotFoundException("Not Found Darling");
        }
    }
}
