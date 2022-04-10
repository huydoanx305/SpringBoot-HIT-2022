package com.hit.buoi3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    Store store;

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<User> users = store.getAllUser();
        return ResponseEntity.ok().body(users);
    }
}
