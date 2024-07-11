package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.CompanyModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY;
import static com.lucasrznd.apigspot.common.CompanyConstants.MAGAZINE_LUIZA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @AfterEach
    public void setUp() {
        COMPANY.setId(null);
    }

    @Test
    public void createCompany_WithValidData_ReturnsCompany() {
        CompanyModel company = companyRepository.save(COMPANY);

        CompanyModel sut = testEntityManager.find(CompanyModel.class, company.getId());

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(COMPANY);
    }

    @Test
    public void createCompany_WithInvalidData_ThrowsException() {
        CompanyModel emptyCompany = new CompanyModel();
        CompanyModel invalidCompany = new CompanyModel(null, "", "", "");

        assertThatThrownBy(() -> companyRepository.save(emptyCompany)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> companyRepository.save(invalidCompany)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createCompany_WithExistingName_ThrowsException() {
        CompanyModel company = testEntityManager.persistFlushFind(COMPANY);
        testEntityManager.detach(company);
        company.setId(null);
        company.setPhoneNumber("4309099090");

        assertThatThrownBy(() -> companyRepository.save(company)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createCompany_WithPhoneNumber_ThrowsException() {
        CompanyModel company = testEntityManager.persistFlushFind(COMPANY);
        testEntityManager.detach(company);
        company.setId(null);
        company.setName("Other Comapny");

        assertThatThrownBy(() -> companyRepository.save(company)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getCompany_ByExistingName_ReturnsCompany() {
        CompanyModel company = testEntityManager.persistFlushFind(COMPANY);

        Optional<CompanyModel> sut = companyRepository.findByName(COMPANY.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(company);
    }

    @Test
    public void getCompany_ByUnexistingName_ReturnsEmpty() {
        Optional<CompanyModel> sut = companyRepository.findByName(COMPANY.getName());

        assertThat(sut).isEmpty();
    }

    @Test
    public void getCompany_ByExistingPhoneNumber_ReturnsCompany() {
        CompanyModel company = testEntityManager.persistFlushFind(COMPANY);

        Optional<CompanyModel> sut = companyRepository.findByPhoneNumber(COMPANY.getPhoneNumber());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(company);
    }

    @Test
    public void getCompany_ByUnexistingPhoneNumber_ReturnsEmpty() {
        Optional<CompanyModel> sut = companyRepository.findByPhoneNumber(COMPANY.getPhoneNumber());

        assertThat(sut).isEmpty();
    }

    @Sql(scripts = "/import_companies.sql")
    @Test
    public void listCompanies_ReturnsCompaniesList() {
        List<CompanyModel> response = companyRepository.findAll();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(3);
        assertThat(response.get(1)).isEqualTo(MAGAZINE_LUIZA);
    }

    @Test
    public void listCompanies_ReturnsEmpty() {
        List<CompanyModel> response = companyRepository.findAll();

        assertThat(response).isEmpty();
    }

    @Test
    public void countCompanies_ReturnsCompaniesQuantity() {
        testEntityManager.persist(COMPANY);
        Long companiesQuantity = companyRepository.count();

        assertThat(companiesQuantity).isEqualTo(1);
    }

    @Test
    public void countCompanies_ReturnsEmpty() {
        Long companiesQuantity = companyRepository.count();

        assertThat(companiesQuantity).isZero();
    }

}
