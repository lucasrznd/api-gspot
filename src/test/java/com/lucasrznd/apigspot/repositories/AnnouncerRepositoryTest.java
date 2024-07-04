package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AnnouncerRepositoryTest {

    @Autowired
    AnnouncerRepository announcerRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void getAnnouncer_ByExistingNameAndPhoneNumber_ReturnsAnnouncer() {
        AnnouncerModel announcer = testEntityManager.persistFlushFind(ANNOUNCER);

        AnnouncerModel sut = announcerRepository.findByNameAndPhoneNumber(announcer.getName(), announcer.getPhoneNumber());

        assertThat(sut).isEqualTo(announcer);
    }

    @Test
    public void getAnnouncer_ByUnexistingNameAndPhoneNumber_ReturnsNull() {
        AnnouncerModel sut = announcerRepository.findByNameAndPhoneNumber(ANNOUNCER.getName(), ANNOUNCER.getPhoneNumber());

        assertThat(sut).isNull();
    }

}