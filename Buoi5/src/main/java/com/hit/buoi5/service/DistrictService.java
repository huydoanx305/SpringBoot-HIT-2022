package com.hit.buoi5.service;

import com.hit.buoi5.dto.DistrictDTO;
import com.hit.buoi5.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistrictService {
    List<District> findAllDistrict();
    District findDistrictById(Long id);
    District createDistrict(Long provinceId, DistrictDTO districtDTO);
    District updateDistrict(DistrictDTO districtDTO, Long id);
    void deleteDistrict(Long id);
    List<District> findDistrictByProvinceId(Long provinceId);
    List<District> createListDistrict(Long provinceId, List<DistrictDTO> districtDTOList);
}
