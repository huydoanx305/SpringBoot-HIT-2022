package com.hit.buoi5.controller;

import com.hit.buoi5.dto.DistrictDTO;
import com.hit.buoi5.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/districts")
    public ResponseEntity<?> getAllDistrict() {
        return ResponseEntity.ok().body(districtService.findAllDistrict());
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(districtService.findDistrictById(id));
    }

    @PostMapping("/districts/{id}")
    public ResponseEntity<?> createDistrict(@PathVariable("id") Long provinceId, @RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.ok().body(districtService.createDistrict(provinceId, districtDTO));
    }

    @PatchMapping("/districts/{id}")
    public ResponseEntity<?> updateDistrict(@RequestBody DistrictDTO districtDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(districtService.updateDistrict(districtDTO, id));
    }

    @DeleteMapping("/districts/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/district-collection/{id}")
    public ResponseEntity<?> createListDistrict(
            @PathVariable("id") Long provinceId,
            @RequestBody List<DistrictDTO> districtDTOList) {
        return ResponseEntity.ok().body(districtService.createListDistrict(provinceId, districtDTOList));
    }
}
