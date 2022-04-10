package com.hit.buoi5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private String name;
    private String type;
//    private String slug;
//    private String nameWithType;
//    private String path;
//    private String pathWithType;
    private Long code;
//    private Long parentCode;
    private Long provinceId;
}
