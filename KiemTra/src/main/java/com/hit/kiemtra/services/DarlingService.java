package com.hit.kiemtra.services;

import com.hit.kiemtra.dto.DarlingDTO;
import com.hit.kiemtra.models.Darling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DarlingService {
    List<Darling> findAllDarlingByStatusOneTwo();
    List<Darling> findAllDarlingOrFindByStatusTwo(Integer status);
    Darling findDarlingById(Long id);
    Darling createDarling(DarlingDTO darlingDTO);
    Darling updateDarling(DarlingDTO darlingDTO, Long id);
    void deleteDarling(Long id);
    List<Darling> findDarlingByAddress(String name);
}
