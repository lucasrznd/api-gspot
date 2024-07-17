package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.response.TopCompanyResponse;
import com.lucasrznd.apigspot.models.TopCompanyOfTheMonth;
import org.springframework.stereotype.Component;

@Component
public class TopCompanyMapper {

    public TopCompanyResponse toDTO(TopCompanyOfTheMonth topCompanyOfTheMonth) {
        if (topCompanyOfTheMonth == null) {
            return null;
        }

        return new TopCompanyResponse(topCompanyOfTheMonth.getCompanyName(), topCompanyOfTheMonth.getSpotCount());
    }

    public TopCompanyOfTheMonth toModel(TopCompanyResponse topCompanyResponse) {
        if (topCompanyResponse == null) {
            return null;
        }

        TopCompanyOfTheMonth topCompanyOfTheMonth = new TopCompanyOfTheMonth();
        topCompanyOfTheMonth.setCompanyName(topCompanyResponse.companyName());
        topCompanyOfTheMonth.setSpotCount(topCompanyResponse.spotCount());

        return topCompanyOfTheMonth;
    }

}
