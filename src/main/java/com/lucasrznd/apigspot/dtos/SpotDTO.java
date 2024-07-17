package com.lucasrznd.apigspot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SpotDTO(Long id,
                      @NotBlank(message = "Title can't be blank.")
                      String title,
                      @NotNull(message = "Company can't be null.")
                      CompanyDTO company,
                      @NotNull(message = "Announcer can't be null.")
                      AnnouncerDTO announcer,
                      LocalDate date,
                      @NotNull(message = "Duration can't be blank.")
                      Double duration,
                      boolean activeContract,
                      BigDecimal price) {
}
