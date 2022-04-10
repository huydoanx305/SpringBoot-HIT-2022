package com.hit.buoi5.service;

import com.hit.buoi5.dto.ProvinceDTO;
import com.hit.buoi5.entity.Province;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ProvinceService {
    List<Province> findAllProvince();
//    public List<Province> findPageProvince(Integer page, Integer size);
    Province findProvinceById(Long id);
    Province createProvince(ProvinceDTO provinceDTO);
    Province updateProvince(ProvinceDTO provinceDTO, Long id);
    void deleteProvince(Long id);
    List<Province> createListProvince(List<ProvinceDTO> provinceDTOList);
}
