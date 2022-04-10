package com.hit.buoi4.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"username"})
public class UserDTO {
    private String username;
    private String password;
    private String fullName;
}
