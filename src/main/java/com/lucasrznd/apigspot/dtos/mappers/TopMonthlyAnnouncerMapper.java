package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.TopMonthlyAnnouncerDTO;
import com.lucasrznd.apigspot.models.TopMonthlyAnnouncer;
import org.springframework.stereotype.Component;

@Component
public class TopMonthlyAnnouncerMapper {

    public TopMonthlyAnnouncerDTO toDTO(TopMonthlyAnnouncer topMonthlyAnnouncer) {
        if (topMonthlyAnnouncer == null) {
            return null;
        }

        return new TopMonthlyAnnouncerDTO(topMonthlyAnnouncer.getAnnouncerName(), topMonthlyAnnouncer.getSpotCount());
    }

    public TopMonthlyAnnouncer toModel(TopMonthlyAnnouncerDTO topMonthlyAnnouncerDTO) {
        if (topMonthlyAnnouncerDTO == null) {
            return null;
        }

        TopMonthlyAnnouncer topMonthlyAnnouncer = new TopMonthlyAnnouncer();
        topMonthlyAnnouncer.setAnnouncerName(topMonthlyAnnouncerDTO.announcerName());
        topMonthlyAnnouncer.setSpotCount(topMonthlyAnnouncerDTO.spotCount());

        return topMonthlyAnnouncer;
    }

}
