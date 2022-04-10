package com.hit.buoi4.controller;

import com.hit.buoi4.entity.User;
import com.hit.buoi4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> findAllUser() {
        return ResponseEntity.ok().body(userService.findAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUserByUsername(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok().body(userService.searchUserByName(name));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(userService.updateUser(user, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
