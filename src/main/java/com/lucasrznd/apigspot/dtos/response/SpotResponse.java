package com.lucasrznd.apigspot.dtos.response;

import java.time.LocalDate;

public record SpotResponse(Long id,
                           String title,
                           String companyName,
                           String companyUrlImage,
                           String announcerName,
                           String announcerUrlImage,
                           LocalDate date,
                           Double duration,
                           boolean activeContract,
                           Double price) {
}
