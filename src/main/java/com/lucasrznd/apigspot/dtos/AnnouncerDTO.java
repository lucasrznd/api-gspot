package com.lucasrznd.apigspot.dtos;

import jakarta.validation.constraints.NotBlank;

public record AnnouncerDTO(Long id,
                           @NotBlank(message = "Name can't be blank.")
                           String name,
                           String phoneNumber,
                           String urlImage) {
}
