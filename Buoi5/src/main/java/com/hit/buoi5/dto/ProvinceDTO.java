package com.hit.buoi5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {
    private String name;
//    private String slug;
    private String type;
//    private String nameWithType;
    private Long code;
}
