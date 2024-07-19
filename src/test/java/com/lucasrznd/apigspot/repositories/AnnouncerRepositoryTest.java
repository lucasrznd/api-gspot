package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.JOHN_DOE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
class AnnouncerRepositoryTest {

    @Autowired
    AnnouncerRepository announcerRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach() {
        ANNOUNCER.setId(null);
    }

    @Test
    public void createAnnouncer_WithValidData_ReturnsAnnouncer() {
        AnnouncerModel announcer = announcerRepository.save(ANNOUNCER);

        AnnouncerModel sut = testEntityManager.find(AnnouncerModel.class, announcer.getId());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(announcer);
    }

    @Test
    public void createAnnouncer_WithExistingName_ThrowsException() {
        AnnouncerModel announcer = testEntityManager.persistFlushFind(ANNOUNCER);
        testEntityManager.detach(announcer);
        announcer.setId(null);
        announcer.setPhoneNumber("43988888888");

        assertThatThrownBy(() -> announcerRepository.save(announcer)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createAnnouncer_WithExistingPhoneNumber_ThrowsException() {
        AnnouncerModel announcer = testEntityManager.persistFlushFind(ANNOUNCER);
        testEntityManager.detach(announcer);
        announcer.setId(null);
        announcer.setName("Other Name");

        assertThatThrownBy(() -> announcerRepository.save(announcer)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getAnnouncer_ByExistingName_ReturnsAnnouncer() {
        AnnouncerModel announcer = testEntityManager.persistFlushFind(ANNOUNCER);

        Optional<AnnouncerModel> sut = announcerRepository.findByName(ANNOUNCER.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(announcer);
    }

    @Test
    public void getAnnouncer_ByUnexistingName_ReturnsEmpty() {
        Optional<AnnouncerModel> sut = announcerRepository.findByName(ANNOUNCER.getName());

        assertThat(sut).isEmpty();
    }

    @Test
    public void getAnnouncer_ByExistingPhoneNumber_ReturnsAnnouncer() {
        AnnouncerModel announcer = testEntityManager.persistFlushFind(ANNOUNCER);

        Optional<AnnouncerModel> sut = announcerRepository.findByName(ANNOUNCER.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(announcer);
    }

    @Test
    public void getAnnouncer_ByUnexistingPhoneNumber_ReturnsEmpty() {
        Optional<AnnouncerModel> sut = announcerRepository.findByName(ANNOUNCER.getName());

        assertThat(sut).isEmpty();
    }

    @Sql(scripts = "/import_announcers.sql")
    @Test
    public void listAnnouncers_ReturnsAnnouncersList() {
        List<AnnouncerModel> response = announcerRepository.findAll();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(3);
        assertThat(response.get(1)).isEqualTo(JOHN_DOE);
    }

    @Test
    public void listAnnouncers_ReturnsEmpty() {
        List<AnnouncerModel> response = announcerRepository.findAll();

        assertThat(response).isEmpty();
    }

    @Test
    public void countAnnouncers_ReturnsAnnouncersQuantity() {
        testEntityManager.persistAndFlush(ANNOUNCER);
        Long announcersQuantity = announcerRepository.count();

        assertThat(announcersQuantity).isEqualTo(1L);
    }

    @Test
    public void countAnnouncers_ReturnsEmpty() {
        Long announcersQuantity = announcerRepository.count();

        assertThat(announcersQuantity).isEqualTo(0L);
    }

}