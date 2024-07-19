package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.SpotModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY;
import static com.lucasrznd.apigspot.common.SpotConstants.SPOT;
import static com.lucasrznd.apigspot.common.SpotConstants.SPOT_WITH_NON_NULL_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class SpotRepositoryTest {

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AnnouncerRepository announcerRepository;

    @AfterEach
    public void setUp() {
        COMPANY.setId(null);
        ANNOUNCER.setId(null);
        SPOT.setId(null);
    }

    @Test
    public void createSpot_WithValidData_ReturnsSpot() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        SpotModel spot = spotRepository.save(SPOT);

        SpotModel sut = testEntityManager.find(SpotModel.class, spot.getId());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(spot);
    }

    @Test
    public void getAmountRaised_ReturnsTotalAmount() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        testEntityManager.persist(SPOT_WITH_NON_NULL_PRICE);

        Double amountRaised = spotRepository.getAmountRaised();

        assertThat(amountRaised).isNotNull();
        assertThat(amountRaised).isEqualTo(25.00);
    }

    @Sql(scripts = "/import_spots.sql")
    @Test
    public void listSpots_ReturnsLatestSpotsOrderByDate() {
        List<SpotModel> response = spotRepository.findLatestSpots();

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getTitle()).isEqualTo(SPOT.getTitle());
    }

    @Test
    public void listSpots_ReturnsEmpty() {
        List<SpotModel> response = spotRepository.findAll();

        assertThat(response).isEmpty();
    }

    @Test
    public void countSpots_ReturnsSpotsQuantity() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);
        testEntityManager.persist(SPOT);

        Long spotsQuantity = spotRepository.count();

        assertThat(spotsQuantity).isEqualTo(1L);
    }

}
