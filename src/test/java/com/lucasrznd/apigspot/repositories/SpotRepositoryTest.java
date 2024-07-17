package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import com.lucasrznd.apigspot.models.CompanyModel;
import com.lucasrznd.apigspot.models.SpotModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER;
import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY;
import static com.lucasrznd.apigspot.common.SpotConstants.SPOT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
    public void createSpot_WithInvalidData_ThrowsException() {
        SpotModel emptySpot = new SpotModel();
        SpotModel invalidSpot = new SpotModel(null, null, new CompanyModel(), new AnnouncerModel(), null, null, false, null);

        assertThatThrownBy(() -> spotRepository.save(emptySpot)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> spotRepository.save(invalidSpot)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidTitle_ThrowsException() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        SpotModel spotWithInvalidTitle = new SpotModel(null, "", COMPANY,
                ANNOUNCER, LocalDate.now(), 0.45, true, 20.00);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidTitle)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidCompany_ThrowsException() {
        announcerRepository.save(ANNOUNCER);

        SpotModel spotWithInvalidCompany = new SpotModel(null, "", COMPANY,
                ANNOUNCER, LocalDate.now(), 0.45, true, 20.00);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidCompany)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidAnnouncer_ThrowsException() {
        companyRepository.save(COMPANY);

        SpotModel spotWithInvalidAnnouncer = new SpotModel(null, "", COMPANY,
                ANNOUNCER, LocalDate.now(), 0.45, true, 20.00);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidAnnouncer)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidDate_ThrowsException() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        SpotModel spotWithInvalidDate = new SpotModel(null, "", COMPANY,
                ANNOUNCER, null, 0.45, true, 20.00);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidDate)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidDuration_ThrowsException() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        SpotModel spotWithInvalidDuration = new SpotModel(null, "", COMPANY,
                ANNOUNCER, LocalDate.now(), null, true, 20.00);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidDuration)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createSpot_WithInvalidPrice_ThrowsException() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        SpotModel spotWithInvalidPrice = new SpotModel(null, "", COMPANY,
                ANNOUNCER, LocalDate.now(), 0.45, true, null);

        assertThatThrownBy(() -> spotRepository.save(spotWithInvalidPrice)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getAmountRaised_ReturnsTotalAmount() {
        companyRepository.save(COMPANY);
        announcerRepository.save(ANNOUNCER);

        testEntityManager.persist(SPOT);

        Double amountRaised = spotRepository.getAmountRaised();

        assertThat(amountRaised).isEqualTo(new BigDecimal("20.00"));
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
