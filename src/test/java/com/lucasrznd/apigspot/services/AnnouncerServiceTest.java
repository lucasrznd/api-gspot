package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.exceptions.announcer.AnnouncerNotFoundException;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.models.AnnouncerModel;
import com.lucasrznd.apigspot.repositories.AnnouncerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER_DTO;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.INVALID_ANNOUNCER_DTO;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.JOHN_DOE;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.JOHN_DOE_DTO;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.NULL_ANNOUNCER_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnouncerServiceTest {

    @InjectMocks
    private AnnouncerService announcerService;

    @Mock
    private AnnouncerMapper announcerMapper;

    @Mock
    private AnnouncerRepository announcerRepository;

    @Test
    public void createAnnouncer_WithValidData_ReturnsAnnouncer() {
        when(announcerRepository.save(announcerMapper.toModel(ANNOUNCER_DTO))).thenReturn(ANNOUNCER);
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        AnnouncerDTO sut = announcerService.save(ANNOUNCER_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void createAnnouncer_WithInvalidData_ThrowsException() {
        when(announcerRepository.save(announcerMapper.toModel(INVALID_ANNOUNCER_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> announcerService.save(INVALID_ANNOUNCER_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createAnnouncer_WithNullData_ThrowsException() {
        when(announcerRepository.save(announcerMapper.toModel(NULL_ANNOUNCER_DTO)))
                .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> announcerService.save(NULL_ANNOUNCER_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void countAnnouncers_ReturnsAnnouncersQuantity() {
        when(announcerRepository.count()).thenReturn(1L);

        Long announcersQuantity = announcerService.count();

        assertThat(announcersQuantity).isEqualTo(1L);
    }

    @Test
    public void countAnnouncers_ReturnsEmpty() {
        Long announcersQuantity = announcerService.count();

        assertThat(announcersQuantity).isEqualTo(0L);
    }

    @Test
    public void updateAnnouncer_WithExistingId_ReturnsUpdatedAnnouncer() {
        when(announcerRepository.findById(2L)).thenReturn(Optional.of(JOHN_DOE));
        when(announcerRepository.save(JOHN_DOE)).thenReturn(JOHN_DOE);
        when(announcerMapper.toDTO(JOHN_DOE)).thenReturn(JOHN_DOE_DTO);

        AnnouncerDTO updatedAnnouncer = announcerService.update(2L, JOHN_DOE_DTO);

        assertThat(updatedAnnouncer).isNotNull();
        assertThat(updatedAnnouncer).isEqualTo(JOHN_DOE_DTO);
    }

    @Test
    public void updateAnnouncer_WithUnexistingId_ThrowsException() {
        when(announcerRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> announcerService.update(2L, JOHN_DOE_DTO)).isInstanceOf(AnnouncerNotFoundException.class);
    }

    @Test
    public void getAnnouncer_ByExistingName_ReturnsAnnouncer() {
        when(announcerRepository.findByName(ANNOUNCER_DTO.name())).thenReturn(Optional.of(ANNOUNCER));
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        Optional<AnnouncerDTO> sut = announcerService.findByName(ANNOUNCER_DTO.name());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void getAnnouncer_ByUnexistingName_ReturnsEmpty() {
        when(announcerRepository.findByName(ANNOUNCER_DTO.name())).thenReturn(Optional.empty());

        Optional<AnnouncerDTO> sut = announcerService.findByName(ANNOUNCER_DTO.name());

        assertThat(sut).isEmpty();
    }

    @Test
    public void getAnnouncer_ByExistingPhoneNumber_ReturnsAnnouncer() {
        when(announcerRepository.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber())).thenReturn(Optional.of(ANNOUNCER));
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        Optional<AnnouncerDTO> sut = announcerService.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void getAnnouncer_ByUnexistingPhoneNumber_ReturnsEmpty() {
        when(announcerRepository.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber())).thenReturn(Optional.empty());

        Optional<AnnouncerDTO> sut = announcerService.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber());

        assertThat(sut).isEmpty();
    }

    @Test
    public void checkIfAnnouncerExists_ByExistingName_ThrowsException() {
        when(announcerRepository.findByName(ANNOUNCER_DTO.name())).thenReturn(Optional.of(ANNOUNCER));
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        assertThatCode(() -> announcerService.checkIfNameAlreadyExists(ANNOUNCER_DTO.name())).isInstanceOf(NameAlreadyExistsException.class);
    }

    @Test
    public void checkIfAnnouncerExists_ByUnexistingName_DoesNotThrowAnyException() {
        when(announcerRepository.findByName(ANNOUNCER_DTO.name())).thenReturn(Optional.empty());

        assertThatCode(() -> announcerService.checkIfNameAlreadyExists(ANNOUNCER_DTO.name())).doesNotThrowAnyException();
    }

    @Test
    public void checkIfAnnouncerExists_ByExistingPhoneNumber_ThrowsException() {
        when(announcerRepository.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber())).thenReturn(Optional.of(ANNOUNCER));
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        assertThatCode(() -> announcerService.checkIfPhoneNumberAlreadyExists(ANNOUNCER_DTO.phoneNumber()))
                .isInstanceOf(PhoneNumberAlreadyExistsException.class);
    }

    @Test
    public void checkIfAnnouncerExists_ByUnexistingPhoneNumber_DoesNotThrowAnyException() {
        when(announcerRepository.findByPhoneNumber(ANNOUNCER_DTO.phoneNumber())).thenReturn(Optional.empty());

        assertThatCode(() -> announcerService.checkIfPhoneNumberAlreadyExists(ANNOUNCER_DTO.phoneNumber()))
                .doesNotThrowAnyException();
    }

    @Test
    public void listAnnouncers_ReturnsAllAnnouncers() {
        List<AnnouncerModel> announcers = List.of(ANNOUNCER);
        when(announcerRepository.findAll()).thenReturn(announcers);
        when(announcerMapper.toDTO(ANNOUNCER)).thenReturn(ANNOUNCER_DTO);

        List<AnnouncerDTO> sut = announcerService.findAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void listAnnouncers_ReturnsEmpty() {
        when(announcerRepository.findAll()).thenReturn(Collections.emptyList());

        List<AnnouncerDTO> sut = announcerService.findAll();

        assertThat(sut).isEmpty();
    }

    @Test
    public void removeAnnouncer_WithExistingId_DoesNotThrowAnyException() {
        when(announcerRepository.findById(1L)).thenReturn(Optional.of(ANNOUNCER));

        assertThatCode(() -> announcerService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    public void removeAnnouncer_WithUnexistingId_ThrowsException() {
        assertThatCode(() -> announcerService.delete(1L)).isInstanceOf(AnnouncerNotFoundException.class);
    }

}
