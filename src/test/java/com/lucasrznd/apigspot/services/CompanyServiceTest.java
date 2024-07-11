package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.CompanyMapper;
import com.lucasrznd.apigspot.exceptions.company.CompanyNotFoundException;
import com.lucasrznd.apigspot.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.lucasrznd.apigspot.common.CompanyConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        CompanyDTO sut = companyService.insert(COMPANY_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(COMPANY_DTO);
    }

    @Test
    public void createCompany_WithInvalidData_ThrowsException() {
        when(companyRepository.save(companyMapper.toModel(INVALID_COMPANY_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> companyService.insert(INVALID_COMPANY_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createCompany_WithNullData_ThrowsException() {
        when(companyRepository.save(companyMapper.toModel(NULL_COMPANY_DTO))).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> companyService.insert(NULL_COMPANY_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void countCompanies_ReturnsCompaniesQuantity() {
        when(companyRepository.count()).thenReturn(1L);

        Long companiesQuantity = companyService.countCompanies();

        assertThat(companiesQuantity).isEqualTo(1L);
    }

    @Test
    public void countCompanies_ReturnsEmpty() {
        Long companiesQuantity = companyService.countCompanies();

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

        assertThatThrownBy(() -> companyService.update(2L, MAGAZINE_LUIZA_DTO)).isInstanceOf(CompanyNotFoundException.class);
    }

    @Test
    public void getCompany_ByExistingName_ReturnsCompany() {
        when(companyRepository.findByName(COMPANY.getName())).thenReturn(Optional.of(COMPANY));
        when(companyMapper.toDTO(COMPANY)).thenReturn(COMPANY_DTO);

        Optional<CompanyDTO> sut = companyService.findByName(COMPANY.getName());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(COMPANY_DTO);
    }

    @Test
    public void getCompany_ByUnexistingName_ReturnsEmpty() {
        when(companyRepository.findByName(COMPANY.getName())).thenReturn(Optional.empty());

        Optional<CompanyDTO> sut = companyService.findByName(COMPANY.getName());

        assertThat(sut).isEmpty();
    }

    @Test
    public void getCompany_ByExistingPhoneNumber_ThrowsException() {
        when(companyRepository.findByPhoneNumber(COMPANY.getPhoneNumber())).thenReturn(Optional.of(COMPANY));
        when(companyMapper.toDTO(COMPANY)).thenReturn(COMPANY_DTO);

        Optional<CompanyDTO> sut = companyService.findByPhoneNumber(COMPANY.getPhoneNumber());

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(COMPANY_DTO);
    }

}
