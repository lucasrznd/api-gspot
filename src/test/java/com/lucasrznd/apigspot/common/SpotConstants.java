package com.lucasrznd.apigspot.common;

import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.models.SpotModel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.*;
import static com.lucasrznd.apigspot.common.CompanyConstants.*;

public class SpotConstants {

    public static final SpotModel SPOT =
            new SpotModel(null, "QUINTA DA CARNE", COMPANY, ANNOUNCER, LocalDate.of(2024, 7, 11), 0.45, true, null);
    public static final SpotModel SPOT_WITH_NON_NULL_PRICE =
            new SpotModel(null, "QUINTA DA CARNE", COMPANY, ANNOUNCER, LocalDate.of(2024, 7, 11), 0.45, true, 25.00);
    public static final SpotModel SPOT_MAGALU =
            new SpotModel(2L, "OFERTAS DE VERAO", MAGAZINE_LUIZA, ANNOUNCER, LocalDate.of(2024, 7, 4), 1.10, true, 25.00);
    public static final List<SpotModel> SPOT_LIST = Arrays.asList(SPOT, SPOT_MAGALU);

    public static final SpotDTO SPOT_DTO =
            new SpotDTO(null, "QUINTA DA CARNE", COMPANY_DTO, ANNOUNCER_DTO, LocalDate.of(2024, 7, 11), 0.45, true, null);
    public static final SpotDTO SPOT_MAGALU_DTO =
            new SpotDTO(2L, "OFERTAS DE VERAO", MAGAZINE_LUIZA_DTO, ANNOUNCER_DTO, LocalDate.of(2024, 7, 4), 1.10, true, 25.00);
    public static final SpotDTO INVALID_SPOT_DTO =
            new SpotDTO(null, "", INVALID_COMPANY_DTO, INVALID_ANNOUNCER_DTO, null, 0.00, false, 0D);
    public static final SpotDTO NULL_SPOT_DTO =
            new SpotDTO(null, null, null, null, null, null, false, null);

    public static final SpotResponse SPOT_RESPONSE =
            new SpotResponse(null, "QUINTA DA CARNE", COMPANY_DTO.name(), COMPANY_DTO.urlImage(), ANNOUNCER_DTO.name(), ANNOUNCER_DTO.urlImage(), LocalDate.of(2024, 7, 11), 0.45, true, 25.00);
    public static final SpotResponse SPOT_MAGALU_RESPONSE =
            new SpotResponse(2L, "OFERTAS DE VERAO", MAGAZINE_LUIZA_DTO.name(), MAGAZINE_LUIZA_DTO.urlImage(), ANNOUNCER_DTO.name(), ANNOUNCER_DTO.urlImage(), LocalDate.of(2024, 7, 4), 1.10, true, 25.00);
    public static final List<SpotResponse> SPOT_RESPONSE_LIST = Arrays.asList(SPOT_RESPONSE, SPOT_MAGALU_RESPONSE);

}
