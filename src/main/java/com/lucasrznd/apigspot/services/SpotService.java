package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.SpotDTO;
import com.lucasrznd.apigspot.dtos.mappers.SpotMapper;
import com.lucasrznd.apigspot.exceptions.spot.SpotNotFoundException;
import com.lucasrznd.apigspot.models.SpotModel;
import com.lucasrznd.apigspot.repositories.SpotRepository;
import com.lucasrznd.apigspot.strategy.SpotPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final List<SpotPrice> spotPrices;

    public SpotService(SpotRepository spotRepository, SpotMapper spotMapper, List<SpotPrice> spotPrices) {
        this.spotRepository = spotRepository;
        this.spotMapper = spotMapper;
        this.spotPrices = spotPrices;
    }

    public List<SpotDTO> findAll() {
        return spotRepository.findAll().stream().map(spotMapper::toDTO).toList();
    }

    public Long count() {
        return spotRepository.count();
    }

    public BigDecimal getAmountRaised() {
        return spotRepository.getAmountRaised();
    }

    public BigDecimal getAmountRaisedMonth() {
        return spotRepository.getAmountRaisedMonth();
    }

    public List<SpotDTO> getLatestSpots() {
        return spotRepository.findLatestSpots().stream().map(spotMapper::toDTO).toList();
    }

    public List<SpotDTO> getByDateRangeAnnouncerAndCompany(LocalDate initialDate, LocalDate finalDate, String companyName, String announcerName) {
        return spotRepository.findByDateRangeAnnouncerAndCompany(initialDate, finalDate, companyName, announcerName)
                .stream().map(spotMapper::toDTO).toList();
    }

    public BigDecimal calculateSpotPrice(Double duration, boolean isActiveContract) {
        if (Objects.nonNull(duration)) {
            for (SpotPrice spotPrice : spotPrices) {
                BigDecimal price = spotPrice.calculatePrice(duration, isActiveContract);
                if (price.compareTo(BigDecimal.ZERO) != 0) {
                    return price;
                } else if (isActiveContract && duration <= 0.35) {
                    return BigDecimal.ZERO;
                } else if (!isActiveContract && duration == 0) {
                    return BigDecimal.ZERO;
                }
            }
        }

        return BigDecimal.ZERO;
    }

    public SpotDTO save(SpotDTO spotDTO) {
        SpotModel spotModel;
        spotModel = spotMapper.toModel(spotDTO);

        if (spotDTO.date() == null) {
            spotModel.setDate(LocalDate.now());
        }

        spotModel.setPrice(calculateSpotPrice(spotModel.getDuration(), spotModel.isActiveContract()));

        return spotMapper.toDTO(spotRepository.save(spotModel));
    }

    public SpotDTO update(Long id, SpotDTO spotDTO) {
        return spotRepository.findById(id)
                .map(spotFound -> {
                    spotFound = spotMapper.toModel(spotDTO);
                    return spotMapper.toDTO(spotRepository.save(spotFound));
                }).orElseThrow(() -> new SpotNotFoundException("Spot not found"));
    }

    public void delete(Long id) {
        spotRepository.delete(spotRepository.findById(id).orElseThrow(() -> new SpotNotFoundException("Spot not found")));
    }

}
