package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.mappers.SpotMapper;
import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.exceptions.common.ResourceNotFoundException;
import com.lucasrznd.apigspot.models.SpotModel;
import com.lucasrznd.apigspot.repositories.SpotRepository;
import com.lucasrznd.apigspot.strategy.SpotPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SpotService {

    private final SpotRepository repository;
    private final SpotMapper mapper;
    private final List<SpotPrice> spotPrices;

    public SpotService(SpotRepository repository, SpotMapper mapper, List<SpotPrice> spotPrices) {
        this.repository = repository;
        this.mapper = mapper;
        this.spotPrices = spotPrices;
    }

    public List<SpotResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Long count() {
        return repository.count();
    }

    public Double getAmountRaised() {
        Double var = repository.getAmountRaised();

        return var == null ? 0D: var;
    }

    public BigDecimal getAmountRaisedMonth() {
        return repository.getAmountRaisedMonth();
    }

    public List<SpotResponse> findLatestSpots() {
        return repository.findLatestSpots().stream().map(mapper::toResponse).toList();
    }

    public List<SpotDTO> getByDateRangeAnnouncerAndCompany(final LocalDate initialDate, final LocalDate finalDate, final String companyName, final String announcerName) {
        return repository.findByDateRangeAnnouncerAndCompany(initialDate, finalDate, companyName, announcerName)
                .stream().map(mapper::toDTO).toList();
    }

    public Double calculateSpotPrice(Double duration, boolean isActiveContract) {
        return spotPrices.stream().mapToDouble(impl -> impl.calculatePrice(duration, isActiveContract)).sum();
    }

    public SpotResponse save(SpotDTO spotDTO) {
        SpotModel spotModel;
        spotModel = mapper.toModel(spotDTO);

        if (spotDTO.date() == null) {
            spotModel.setDate(LocalDate.now());
        }

        spotModel.setPrice(calculateSpotPrice(spotModel.getDuration(), spotModel.isActiveContract()));

        return mapper.toResponse(repository.save(spotModel));
    }

    public SpotResponse update(final Long id, SpotDTO spotDTO) {
        return repository.findById(id)
                .map(spotFound -> {
                    spotFound.setTitle(spotDTO.title());
                    spotFound.setCompany(mapper.companyToModel(spotDTO.company()));
                    spotFound.setAnnouncer(mapper.announcerToModel(spotDTO.announcer()));
                    spotFound.setDate(spotDTO.date() != null ? spotDTO.date() : LocalDate.now());
                    spotFound.setDuration(spotDTO.duration());
                    spotFound.setActiveContract(spotDTO.activeContract());
                    spotFound.setPrice(calculateSpotPrice(spotDTO.duration(), spotDTO.activeContract()));
                    return mapper.toResponse(repository.save(spotFound));
                }).orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + SpotDTO.class.getSimpleName()));
    }

    public void delete(final Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + SpotDTO.class.getSimpleName())));
    }

}
