package com.hit.kiemtra.controllers;

import com.hit.kiemtra.dto.DarlingDTO;
import com.hit.kiemtra.services.DarlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class DarlingController {
    @Autowired
    private DarlingService darlingService;

    @GetMapping("/darlings")
    public ResponseEntity<?> findAllProvince() {
        return ResponseEntity.ok().body(darlingService.findAllDarling());
    }

    @GetMapping("/darlings/current")
    public ResponseEntity<?> findAllDarlingByStatusOneTwo() {
        return ResponseEntity.ok().body(darlingService.findAllDarlingByStatusOneTwo());
    }

    @GetMapping("/darlings/status")
    public ResponseEntity<?> findAllDarlingByStatusTwo(@RequestParam(value = "status") Integer status) {
        return ResponseEntity.ok().body(darlingService.findAllDarlingByStatusTwo(status));
    }

    @GetMapping("/darlings/{id}")
    public ResponseEntity<?> findDarlingById(@PathVariable Long id) {
        return ResponseEntity.ok().body(darlingService.findDarlingById(id));
    }

    @PostMapping("/darlings")
    public ResponseEntity<?> createDarling(@RequestBody DarlingDTO darlingDTO) {
        return ResponseEntity.ok().body(darlingService.createDarling(darlingDTO));
    }

    @PatchMapping("/darlings/{id}")
    public ResponseEntity<?> updateDarling(@RequestBody DarlingDTO darlingDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(darlingService.updateDarling(darlingDTO, id));
    }

    @DeleteMapping("/darlings/{id}")
    public ResponseEntity<?> deleteDarling(@PathVariable Long id) {
        darlingService.deleteDarling(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/darlings/addresses")
    public ResponseEntity<?> findDarlingByAddress(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(darlingService.findDarlingByAddress(name));
    }
}