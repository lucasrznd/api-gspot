package com.lucasrznd.apigspot.dtos.response;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SpotResponse(Long id,
                           String title,
                           String companyName,
                           String announcerName,
                           LocalDate date,
                           Double duration,
                           boolean activeContract,
                           Double price) {
}
