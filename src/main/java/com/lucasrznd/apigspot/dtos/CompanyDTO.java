package com.lucasrznd.apigspot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CompanyDTO(Long id,
                         @NotEmpty(message = "Name can't be empty.")
                         @NotBlank(message = "Name can't be blank.") String name,
                         String phoneNumber,
                         String urlImage) {
}
