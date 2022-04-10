package com.hit.kiemtra.controllers;

import com.hit.kiemtra.dto.AddressDTO;
import com.hit.kiemtra.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<?> findAllAddress() {
        return ResponseEntity.ok().body(addressService.findAllAddress());
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.findAddressById(id));
    }

    @PostMapping("/addresses/{darlingId}")
    public ResponseEntity<?> createAddress(@PathVariable Long darlingId, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok().body(addressService.createAddress(addressDTO,darlingId));
    }

    @PatchMapping("/addresses/{darlingId}/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO addressDTO, @PathVariable Long darlingId, @PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.updateAddress(addressDTO, darlingId, id));

    }

    @DeleteMapping("/addresses/{darlingId}/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long darlingId, @PathVariable Long id) {
        addressService.deleteAddress(darlingId, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/darlings/{id}/addresses")
    public ResponseEntity<?> findDarlingByAddress(@PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.findAddressByDarlingId(id));
    }

}
