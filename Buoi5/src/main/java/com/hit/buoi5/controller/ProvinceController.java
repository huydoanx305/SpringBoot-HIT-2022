package com.hit.buoi5.controller;

import com.hit.buoi5.dto.ProvinceDTO;
import com.hit.buoi5.service.DistrictService;
import com.hit.buoi5.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;


    @GetMapping("/provinces")
    public ResponseEntity<?> findAllProvince() {
        return ResponseEntity.ok().body(provinceService.findAllProvince());
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPageProvince(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok().body(provinceService.findPageProvince(page, size));
    }

    @GetMapping("/provinces/{id}")
    public ResponseEntity<?> findProvinceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(provinceService.findProvinceById(id));
    }

    @PostMapping("/provinces")
    public ResponseEntity<?> createProvince(@RequestBody ProvinceDTO provinceDTO) {
        return ResponseEntity.ok().body(provinceService.createProvince(provinceDTO));
    }

    @PatchMapping("/provinces/{id}")
    public ResponseEntity<?> updateProvince(@RequestBody ProvinceDTO provinceDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(provinceService.updateProvince(provinceDTO, id));
    }

    @DeleteMapping("/provinces/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/provinces/{id}/districts")
    public ResponseEntity<?> findDistrictByProvinceId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(districtService.findDistrictByProvinceId(id));
    }

    @PostMapping("/province-collection")
    public ResponseEntity<?> createListProvince(@RequestBody List<ProvinceDTO> provinceDTOList) {
        return ResponseEntity.ok().body(provinceService.createListProvince(provinceDTOList));
    }
}
