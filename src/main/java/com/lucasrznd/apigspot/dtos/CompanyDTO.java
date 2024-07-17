package com.lucasrznd.apigspot.dtos;

import jakarta.validation.constraints.NotBlank;

public record CompanyDTO(Long id,
                         @NotBlank(message = "Name can't be blank.")
                         String name,
                         @NotBlank(message = "Phone Number can't be blank.")
                         String phoneNumber,
                         String urlImage) {
}
