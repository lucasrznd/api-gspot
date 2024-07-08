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

import static com.lucasrznd.apigspot.common.AnnouncerConstants.*;
import static org.assertj.core.api.Assertions.*;
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

        AnnouncerDTO sut = announcerService.insert(ANNOUNCER_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void createAnnouncer_WithInvalidData_ThrowsException() {
        when(announcerRepository.save(announcerMapper.toModel(INVALID_ANNOUNCER_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> announcerService.insert(INVALID_ANNOUNCER_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createAnnouncer_WithNullData_ThrowsException() {
        when(announcerRepository.save(announcerMapper.toModel(NULL_ANNOUNCER_DTO)))
                .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> announcerService.insert(NULL_ANNOUNCER_DTO)).isInstanceOf(RuntimeException.class);
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

        List<AnnouncerDTO> sut = announcerService.selectAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(ANNOUNCER_DTO);
    }

    @Test
    public void listAnnouncers_ReturnsEmpty() {
        when(announcerRepository.findAll()).thenReturn(Collections.emptyList());

        List<AnnouncerDTO> sut = announcerService.selectAll();

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
