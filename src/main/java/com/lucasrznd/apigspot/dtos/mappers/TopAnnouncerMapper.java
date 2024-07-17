package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.response.TopAnnouncerResponse;
import com.lucasrznd.apigspot.models.TopAnnouncerOfTheMonth;
import org.springframework.stereotype.Component;

@Component
public class TopAnnouncerMapper {

    public TopAnnouncerResponse toDTO(TopAnnouncerOfTheMonth topAnnouncerOfTheMonth) {
        if (topAnnouncerOfTheMonth == null) {
            return null;
        }

        return new TopAnnouncerResponse(topAnnouncerOfTheMonth.getAnnouncerName(), topAnnouncerOfTheMonth.getSpotCount());
    }

    public TopAnnouncerOfTheMonth toModel(TopAnnouncerResponse topAnnouncerResponse) {
        if (topAnnouncerResponse == null) {
            return null;
        }

        TopAnnouncerOfTheMonth topAnnouncerOfTheMonth = new TopAnnouncerOfTheMonth();
        topAnnouncerOfTheMonth.setAnnouncerName(topAnnouncerResponse.announcerName());
        topAnnouncerOfTheMonth.setSpotCount(topAnnouncerResponse.spotCount());

        return topAnnouncerOfTheMonth;
    }

}
