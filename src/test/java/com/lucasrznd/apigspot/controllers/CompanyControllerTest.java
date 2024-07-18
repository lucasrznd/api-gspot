package com.lucasrznd.apigspot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrznd.apigspot.controllers.impl.CompanyController;
import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.exceptions.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.ResourceNotFoundException;
import com.lucasrznd.apigspot.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANIES_DTO_LIST;
import static com.lucasrznd.apigspot.common.CompanyConstants.COMPANY_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CompanyService companyService;

    @Test
    public void createCompany_WithValidData_ReturnsCreated() throws Exception {
        when(companyService.save(COMPANY_DTO)).thenReturn(COMPANY_DTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/company").content(objectMapper.writeValueAsString(COMPANY_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(COMPANY_DTO.name()))
                .andExpect(jsonPath("$.phoneNumber").value(COMPANY_DTO.phoneNumber()));
    }

    @Test
    public void createCompany_WithInvalidData_ReturnsBadRequest() throws Exception {
        CompanyDTO emptyCompany = new CompanyDTO(null, null, null, null);
        CompanyDTO invalidCompany = new CompanyDTO(null, "", "", "");

        mockMvc.perform(post("/company").content(objectMapper.writeValueAsString(emptyCompany))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mockMvc.perform(post("/company").content(objectMapper.writeValueAsString(invalidCompany))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void createCompany_WithExistingName_ReturnsConflict() throws Exception {
        when(companyService.save(any())).thenThrow(NameAlreadyExistsException.class);

        mockMvc.perform(post("/company").content(objectMapper.writeValueAsString(COMPANY_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void createCompany_WithExistingPhoneNumber_ReturnsConflict() throws Exception {
        when(companyService.save(any())).thenThrow(PhoneNumberAlreadyExistsException.class);

        mockMvc.perform(post("/company").content(objectMapper.writeValueAsString(COMPANY_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void countCompanies_ReturnsCompaniesQuantity() throws Exception {
        when(companyService.count()).thenReturn(1L);

        mockMvc.perform(get("/company/count"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").value(1L));
    }

    @Test
    public void updateCompany_WithExistingId_ReturnsUpdatedCompany() throws Exception {
        when(companyService.update(1L, COMPANY_DTO)).thenReturn(COMPANY_DTO);

        mockMvc.perform(put("/company/" + 1).content(objectMapper.writeValueAsString(COMPANY_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value(COMPANY_DTO.name()))
                .andExpect(jsonPath("$.phoneNumber").value(COMPANY_DTO.phoneNumber()));
    }

    @Test
    public void updateCompany_WithUnexistingId_ReturnsNotFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(companyService).update(1L, COMPANY_DTO);

        mockMvc.perform(put("/company/" + 1).content(objectMapper.writeValueAsString(COMPANY_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    public void listCompanies_ReturnsCompanies() throws Exception {
        when(companyService.findAll()).thenReturn(COMPANIES_DTO_LIST);

        mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deleteCompany_WithExistingId_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/company/" + 1))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void deleteCompany_WithUnexistingId_ReturnsNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(companyService).delete(1L);

        mockMvc.perform(delete("/company" + 1))
                .andExpect(status().isNotFound());
    }

}
