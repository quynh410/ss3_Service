package com.ra.ss3s.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EDTO {
    private Integer id;
    private String name;
    private Double salary;
    private String phoneNumber;
}
