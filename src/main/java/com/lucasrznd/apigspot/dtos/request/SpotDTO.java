package com.lucasrznd.apigspot.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SpotDTO(Long id,
                      @NotEmpty(message = "Title can't be blank.")
                      String title,
                      @NotNull(message = "Company can't be null.")
                      CompanyDTO company,
                      @NotNull(message = "Announcer can't be null.")
                      AnnouncerDTO announcer,
                      LocalDate date,
                      @NotNull(message = "Duration can't be null.")
                      Double duration,
                      boolean activeContract,
                      Double price) {
}
