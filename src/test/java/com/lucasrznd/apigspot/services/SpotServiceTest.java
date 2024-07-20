package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.mappers.SpotMapper;
import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.exceptions.ResourceNotFoundException;
import com.lucasrznd.apigspot.models.SpotModel;
import com.lucasrznd.apigspot.repositories.SpotRepository;
import com.lucasrznd.apigspot.strategy.SpotPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER_DTO;
import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY;
import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY_DTO;
import static com.lucasrznd.apigspot.common.SpotConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpotServiceTest {

    @InjectMocks
    private SpotService service;

    @Mock
    private SpotMapper mapper;

    @Mock
    private SpotRepository repository;

    @Mock
    private List<SpotPrice> spotPrices;

    @Test
    public void createSpot_WithValidData_ReturnsSpot() {
        when(mapper.toModel(SPOT_DTO)).thenReturn(SPOT);
        when(repository.save(SPOT)).thenReturn(SPOT);
        when(mapper.toResponse(SPOT)).thenReturn(SPOT_RESPONSE);

        SpotResponse sut = service.save(SPOT_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(SPOT_RESPONSE);
        assertThat(sut.price()).isEqualTo(25.00);
    }

    @Test
    public void createSpot_WithNullDate_ReturnsSpot() {
        SpotDTO spotDTO = new SpotDTO(null, "QUINTA DA CARNE",
                COMPANY_DTO, ANNOUNCER_DTO, null, 0.45,
                true, null);

        SpotModel spotModel = new SpotModel(null, "QUINTA DA CARNE",
                COMPANY, ANNOUNCER, LocalDate.of(2024, 7, 11),
                0.45, true, 25.00);

        SpotResponse spotResponse = new SpotResponse(spotModel.getId(), spotModel.getTitle(),
                spotModel.getCompany().getName(), spotModel.getCompany().getUrlImage(),
                spotModel.getAnnouncer().getName(), spotModel.getAnnouncer().getUrlImage(),
                spotModel.getDate(), spotModel.getDuration(),
                spotModel.isActiveContract(), spotModel.getPrice());

        when(mapper.toModel(spotDTO)).thenReturn(spotModel);
        when(repository.save(spotModel)).thenReturn(spotModel);
        when(mapper.toResponse(spotModel)).thenReturn(spotResponse);

        SpotResponse sut = service.save(spotDTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(spotResponse);
        assertThat(sut.date()).isNotNull();
    }

    @Test
    public void getTotalAmountRaised_ReturnsAmount() {
        when(repository.getAmountRaised()).thenReturn(50.00);

        Double totalAmountRaised = service.getTotalAmountRaised();

        assertThat(totalAmountRaised).isEqualTo(50.00);
    }

    @Test
    public void getTotalAmountRaised_ReturnsZero() {
        when(repository.getAmountRaised()).thenReturn(null);
        Double totalAmountRaised = service.getTotalAmountRaised();

        assertThat(totalAmountRaised).isZero();
    }

    @Test
    public void getAmountRaisedCurrentMonth_ReturnsAmount() {
        when(repository.getAmountRaisedMonth()).thenReturn(25.00);

        Double amountRaisedCurrentMonth = service.getAmountRaisedCurrentMonth();

        assertThat(amountRaisedCurrentMonth).isEqualTo(25.00);
    }

    @Test
    public void getAmountRaisedCurrentMonth_ReturnsEmpty() {
        when(repository.getAmountRaisedMonth()).thenReturn(null);
        Double amountRaisedCurrentMonth = service.getAmountRaisedCurrentMonth();

        assertThat(amountRaisedCurrentMonth).isZero();
    }

    @Test
    public void countSpots_ReturnsSpotsQuantity() {
        when(repository.count()).thenReturn(1L);

        Long spotsQuantity = service.count();

        assertThat(spotsQuantity).isEqualTo(1L);
    }

    @Test
    public void countSpots_ReturnsEmpty() {
        Long spotsQuantity = service.count();

        assertThat(spotsQuantity).isZero();
    }

    @Test
    public void updateSpot_WithExistingId_ReturnsUpdatedSpot() {
        when(repository.findById(2L)).thenReturn(Optional.of(SPOT_MAGALU));
        when(repository.save(SPOT_MAGALU)).thenReturn(SPOT_MAGALU);
        when(mapper.toResponse(SPOT_MAGALU)).thenReturn(SPOT_MAGALU_RESPONSE);

        SpotResponse updatedSpot = service.update(2L, SPOT_MAGALU_DTO);

        assertThat(updatedSpot).isNotNull();
        assertThat(updatedSpot).isEqualTo(SPOT_MAGALU_RESPONSE);
    }

    @Test
    public void updateSpot_WithUnexistingId_ThrowsException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(2L, SPOT_MAGALU_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void listSpots_ByDateRangeAnnouncerAndCompany_ReturnsSpots() {
        LocalDate initialDate = LocalDate.of(2024, 7, 3);
        LocalDate finalDate = LocalDate.of(2024, 7, 12);

        when(repository.findByDateRangeAnnouncerAndCompany(initialDate, finalDate, "", ""))
                .thenReturn(SPOT_LIST);
        when(mapper.toResponse(SPOT)).thenReturn(SPOT_RESPONSE);
        when(mapper.toResponse(SPOT_MAGALU)).thenReturn(SPOT_MAGALU_RESPONSE);

        List<SpotResponse> response = service.findByDateRangeAnnouncerAndCompany(initialDate, finalDate, "", "");

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0)).isEqualTo(SPOT_RESPONSE);
    }

    @Test
    public void listLatestSpots_ReturnsSpots() {
        when(repository.findLatestSpots()).thenReturn(SPOT_LIST);
        when(mapper.toResponse(SPOT)).thenReturn(SPOT_RESPONSE);
        when(mapper.toResponse(SPOT_MAGALU)).thenReturn(SPOT_MAGALU_RESPONSE);

        List<SpotResponse> response = service.findLatestSpots();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(1)).isEqualTo(SPOT_MAGALU_RESPONSE);
    }

    @Test
    public void listSpots_ReturnsEmpty() {
        List<SpotResponse> response = service.findAll();

        assertThat(response).isEmpty();
    }

    @Test
    public void removeSpot_WithExistingId_DoesNotThrowAnyException() {
        when(repository.findById(SPOT_MAGALU.getId())).thenReturn(Optional.of(SPOT_MAGALU));

        assertThatCode(() -> service.delete(SPOT_MAGALU.getId())).doesNotThrowAnyException();
    }

    @Test
    public void removeSpot_WithUnexistingId_ThrowsException() {
        assertThatCode(() -> service.delete(3L)).isInstanceOf(ResourceNotFoundException.class);
    }

}
