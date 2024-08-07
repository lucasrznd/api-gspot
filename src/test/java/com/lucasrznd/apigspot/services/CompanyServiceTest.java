package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.CompanyMapper;
import com.lucasrznd.apigspot.exceptions.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.ResourceNotFoundException;
import com.lucasrznd.apigspot.models.CompanyModel;
import com.lucasrznd.apigspot.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lucasrznd.apigspot.common.CompanyConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void createCompany_WithValidData_ReturnsCompany() {
        when(companyRepository.save(companyMapper.toModel(COMPANY_DTO))).thenReturn(COMPANY);
        when(companyMapper.toDTO(COMPANY)).thenReturn(COMPANY_DTO);

        CompanyDTO sut = companyService.save(COMPANY_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(COMPANY_DTO);
    }

    @Test
    public void createCompany_WithInvalidData_ThrowsException() {
        when(companyRepository.save(companyMapper.toModel(INVALID_COMPANY_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> companyService.save(INVALID_COMPANY_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createCompany_WithNullData_ThrowsException() {
        when(companyRepository.save(companyMapper.toModel(NULL_COMPANY_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> companyService.save(NULL_COMPANY_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void countCompanies_ReturnsCompaniesQuantity() {
        when(companyRepository.count()).thenReturn(1L);

        Long companiesQuantity = companyService.count();

        assertThat(companiesQuantity).isEqualTo(1L);
    }

    @Test
    public void countCompanies_ReturnsEmpty() {
        Long companiesQuantity = companyService.count();

        assertThat(companiesQuantity).isZero();
    }

    @Test
    public void updateCompany_WithExistingId_ReturnsUpdatedCompany() {
        when(companyRepository.findById(2L)).thenReturn(Optional.of(MAGAZINE_LUIZA));
        when(companyRepository.save(MAGAZINE_LUIZA)).thenReturn(MAGAZINE_LUIZA);
        when(companyMapper.toDTO(MAGAZINE_LUIZA)).thenReturn(MAGAZINE_LUIZA_DTO);

        CompanyDTO updatedCompany = companyService.update(2L, MAGAZINE_LUIZA_DTO);

        assertThat(updatedCompany).isNotNull();
        assertThat(updatedCompany).isEqualTo(MAGAZINE_LUIZA_DTO);
    }

    @Test
    public void updateCompany_WithUnexistingId_ThrowsException() {
        when(companyRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> companyService.update(2L, MAGAZINE_LUIZA_DTO)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void checkIfCompanyExists_ByExistingName_ThrowsException() {
        when(companyRepository.findByName(COMPANY.getName())).thenReturn(Optional.of(COMPANY));

        assertThatCode(() -> companyService.checkIfNameAlreadyExists(COMPANY.getName())).isInstanceOf(NameAlreadyExistsException.class);
    }

    @Test
    public void checkIfCompanyExists_ByUnexistingName_DoesNotThrowAnyException() {
        assertThatCode(() -> companyService.checkIfNameAlreadyExists(COMPANY.getName())).doesNotThrowAnyException();
    }

    @Test
    public void checkIfCompanyExists_ByExistingPhoneNumber_ThrowsException() {
        when(companyRepository.findByPhoneNumber(COMPANY.getPhoneNumber())).thenReturn(Optional.of(COMPANY));

        assertThatCode(() -> companyService.checkIfPhoneNumberAlreadyExists(COMPANY.getPhoneNumber())).isInstanceOf(PhoneNumberAlreadyExistsException.class);
    }

    @Test
    public void checkIfCompanyExists_ByUnexistingPhoneNumber_ThrowsException() {
        assertThatCode(() -> companyService.checkIfPhoneNumberAlreadyExists(COMPANY.getPhoneNumber())).doesNotThrowAnyException();
    }

    @Test
    public void listCompanies_ReturnsAllCompanies() {
        List<CompanyModel> companies = List.of(COMPANY);
        when(companyRepository.findAll()).thenReturn(companies);
        when(companyMapper.toDTO(COMPANY)).thenReturn(COMPANY_DTO);

        List<CompanyDTO> sut = companyService.findAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut.size()).isEqualTo(1);
        assertThat(sut.get(0)).isEqualTo(COMPANY_DTO);
    }

    @Test
    public void listCompanies_ReturnsEmpty() {
        when(companyRepository.findAll()).thenReturn(Collections.emptyList());

        List<CompanyDTO> sut = companyService.findAll();

        assertThat(sut).isEmpty();
    }

    @Test
    public void removeCompany_WithExistingId_DoesNotThrowAnyException() {
        when(companyRepository.findById(COMPANY.getId())).thenReturn(Optional.of(COMPANY));

        assertThatCode(() -> companyService.delete(COMPANY.getId())).doesNotThrowAnyException();
    }

    @Test
    public void removeCompany_WithUnexistingId_ThrowsException() {
        assertThatCode(() -> companyService.delete(COMPANY.getId())).isInstanceOf(ResourceNotFoundException.class);
    }

}
