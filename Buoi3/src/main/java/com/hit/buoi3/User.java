package com.hit.buoi3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"username","password"})
public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;
}
