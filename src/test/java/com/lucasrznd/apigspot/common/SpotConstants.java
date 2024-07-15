package com.lucasrznd.apigspot.common;

import com.lucasrznd.apigspot.dtos.SpotDTO;
import com.lucasrznd.apigspot.models.SpotModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.*;
import static com.lucasrznd.apigspot.common.CompanyConstants.*;

public class SpotConstants {

    public static final SpotModel SPOT =
            new SpotModel(null, "QUINTA DA CARNE", COMPANY, ANNOUNCER, LocalDate.of(2024, 7, 11), 0.45, true, new BigDecimal("20.00"));
    public static final SpotModel SPOT_MAGALU =
            new SpotModel(2L, "OFERTAS DE VERAO", MAGAZINE_LUIZA, ANNOUNCER, LocalDate.of(2024, 7, 4), 1.10, true, new BigDecimal("25.00"));

    public static final SpotDTO SPOT_DTO =
            new SpotDTO(null, "QUINTA DA CARNE", COMPANY_DTO, ANNOUNCER_DTO, LocalDate.of(2024, 7, 11), 0.45, true, new BigDecimal("20.00"));
    public static final SpotDTO SPOT_MAGALU_DTO =
            new SpotDTO(2L, "OFERTAS DE VERAO", MAGAZINE_LUIZA_DTO, ANNOUNCER_DTO, LocalDate.of(2024, 7, 4), 1.10, true, new BigDecimal("25.00"));
    public static final SpotDTO INVALID_SPOT_DTO =
            new SpotDTO(null, "", INVALID_COMPANY_DTO, INVALID_ANNOUNCER_DTO, null, 0.00, false, BigDecimal.ZERO);
    public static final SpotDTO NULL_SPOT_DTO =
            new SpotDTO(null, null, null, null, null, null, false, null);

    public static final List<SpotDTO> SPOT_DTO_LIST = Arrays.asList(SPOT_DTO, SPOT_MAGALU_DTO);

}
