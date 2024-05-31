package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.models.AnnouncerModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AnnouncerRepositoryTest {

    @Autowired
    AnnouncerRepository announcerRepository;

    @Autowired
    EntityManager entityManager;

    AnnouncerMapper announcerMapper = new AnnouncerMapper();

    @Test
    @DisplayName("Should get specific Announcer by Name and Number successfully")
    void findByNameAndPhoneNumberSuccess() {
        AnnouncerDTO announcerDTO = new AnnouncerDTO(null, "Lucas Rezende", "43999999999", "");
        this.createAnnouncer(announcerDTO);

        AnnouncerModel announcerFound = this.announcerRepository.findByNameAndPhoneNumber(announcerDTO.name(), announcerDTO.phoneNumber());

        assertThat(announcerFound != null).isTrue();
    }

    @Test
    @DisplayName("Should not get Announcer from DB when announcer not exists")
    void findByNameAndPhoneNumberError() {
        AnnouncerDTO announcerDTO = new AnnouncerDTO(1L, "Lucas Rezende", "43999999999", "");

        AnnouncerModel announcerFound = this.announcerRepository.findByNameAndPhoneNumber(announcerDTO.name(), announcerDTO.phoneNumber());

        assertThat(announcerFound == null).isTrue();
    }

    private AnnouncerModel createAnnouncer(AnnouncerDTO announcerDTO) {
        AnnouncerModel newAnnouncer = announcerMapper.toModel(announcerDTO);

        entityManager.persist(newAnnouncer);
        return newAnnouncer;
    }
}