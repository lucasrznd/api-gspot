package com.lucasrznd.apigspot.common;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.models.AnnouncerModel;

public class AnnouncerConstants {

    public static final AnnouncerModel ANNOUNCER = new AnnouncerModel(null, "Lucas Rezende", "43999999999", "");
    public static final AnnouncerDTO ANNOUNCER_DTO = new AnnouncerDTO(null, "Lucas Rezende", "43999999999", "");

    public static final AnnouncerModel JOHN_DOE = new AnnouncerModel(2L, "John Doe", "43988888888", "");

}
