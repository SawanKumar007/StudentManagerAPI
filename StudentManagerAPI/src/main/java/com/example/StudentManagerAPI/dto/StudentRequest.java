package com.example.StudentManagerAPI.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String age;

    @NotBlank
    private String studentClass;

    @NotNull
    private Long phoneNumber;
}
