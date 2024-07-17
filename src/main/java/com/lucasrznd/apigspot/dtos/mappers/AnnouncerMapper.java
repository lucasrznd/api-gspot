package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.springframework.stereotype.Component;

@Component
public class AnnouncerMapper {

    public AnnouncerDTO toDTO(AnnouncerModel announcer) {
        if (announcer == null) {
            return null;
        }

        return new AnnouncerDTO(announcer.getId(), announcer.getName(), announcer.getPhoneNumber(), announcer.getUrlImage());
    }

    public AnnouncerModel toModel(AnnouncerDTO announcerDTO) {
        if (announcerDTO == null) {
            return null;
        }

        AnnouncerModel announcerModel = new AnnouncerModel();
        announcerModel.setId(announcerDTO.id());
        announcerModel.setName(announcerDTO.name());
        announcerModel.setPhoneNumber(announcerDTO.phoneNumber());
        announcerModel.setUrlImage(announcerDTO.urlImage());

        return announcerModel;
    }

}
