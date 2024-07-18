package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.models.AnnouncerModel;
import com.lucasrznd.apigspot.models.CompanyModel;
import com.lucasrznd.apigspot.models.SpotModel;
import org.springframework.stereotype.Component;

@Component
public class SpotMapper {

    private final CompanyMapper companyMapper;
    private final AnnouncerMapper announcerMapper;

    public SpotMapper(CompanyMapper companyMapper, AnnouncerMapper announcerMapper) {
        this.companyMapper = companyMapper;
        this.announcerMapper = announcerMapper;
    }

    public SpotDTO toDTO(SpotModel spot) {
        if (spot == null) {
            return null;
        }

        return new SpotDTO(spot.getId(), spot.getTitle(), companyMapper.toDTO(spot.getCompany()), announcerMapper.toDTO(spot.getAnnouncer()),
                spot.getDate(), spot.getDuration(), spot.isActiveContract(), spot.getPrice());
    }

    public SpotResponse toResponse(SpotModel spot) {
        if (spot == null) {
            return null;
        }

        return new SpotResponse(spot.getId(), spot.getTitle(), spot.getCompany().getName(), spot.getCompany().getUrlImage(),
                spot.getAnnouncer().getName(), spot.getAnnouncer().getUrlImage(), spot.getDate(), spot.getDuration(), spot.isActiveContract(),
                spot.getPrice());
    }

    public SpotModel toModel(SpotDTO spotDTO) {
        if (spotDTO == null) {
            return null;
        }

        SpotModel spotModel = new SpotModel();
        spotModel.setId(spotDTO.id());
        spotModel.setTitle(spotDTO.title());
        spotModel.setCompany(companyMapper.toModel(spotDTO.company()));
        spotModel.setAnnouncer(announcerMapper.toModel(spotDTO.announcer()));
        spotModel.setDate(spotDTO.date());
        spotModel.setDuration(spotDTO.duration());
        spotModel.setActiveContract(spotDTO.activeContract());
        spotModel.setPrice(spotDTO.price());

        return spotModel;
    }

    public CompanyModel companyToModel(CompanyDTO company) {
        return companyMapper.toModel(company);
    }

    public AnnouncerModel announcerToModel(AnnouncerDTO announcer) {
        return announcerMapper.toModel(announcer);
    }

}
