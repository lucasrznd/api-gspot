package com.lucasrznd.apigspot.common;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.models.AnnouncerModel;

import java.util.Arrays;
import java.util.List;

public class AnnouncerConstants {

    public static final AnnouncerModel ANNOUNCER = new AnnouncerModel(null, "Lucas Rezende", "43999999999", "");
    public static final AnnouncerDTO ANNOUNCER_DTO = new AnnouncerDTO(null, "Lucas Rezende", "43999999999", "");
    public static final AnnouncerDTO INVALID_ANNOUNCER_DTO = new AnnouncerDTO(null, "", "", "");
    public static final AnnouncerDTO NULL_ANNOUNCER_DTO = new AnnouncerDTO(null, null, null, null);

    public static final AnnouncerModel JOHN_DOE = new AnnouncerModel(2L, "John Doe", "43988888888", "");
    public static final AnnouncerDTO GALVAO_BUENO = new AnnouncerDTO(3L, "Galvao Bueno", "439777777777", "");

    public static final List<AnnouncerDTO> ANNOUNCERS_DTO_LIST = Arrays.asList(ANNOUNCER_DTO, GALVAO_BUENO);

}
