package com.lucasrznd.apigspot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.exceptions.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.ResourceNotFoundException;
import com.lucasrznd.apigspot.services.AnnouncerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCERS_DTO_LIST;
import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnnouncerController.class)
public class AnnouncerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnnouncerService announcerService;

    @Test
    public void createAnnouncer_WithValidData_ReturnsCreated() throws Exception {
        when(announcerService.save(ANNOUNCER_DTO)).thenReturn(ANNOUNCER_DTO);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(ANNOUNCER_DTO.name()))
                .andExpect(jsonPath("$.phoneNumber").value(ANNOUNCER_DTO.phoneNumber()));
    }

    @Test
    public void createAnnouncer_WithInvalidData_ReturnsBadRequest() throws Exception {
        AnnouncerDTO emptyAnnouncer = new AnnouncerDTO(null, null, null, null);
        AnnouncerDTO invalidAnnouncer = new AnnouncerDTO(null, "", "", "");

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(emptyAnnouncer))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(invalidAnnouncer))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void createAnnouncer_WithExistingName_ReturnsConflict() throws Exception {
        when(announcerService.save(any())).thenThrow(NameAlreadyExistsException.class);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void createAnnouncer_WithExistingPhoneNumber_ReturnsConflict() throws Exception {
        when(announcerService.save(any())).thenThrow(PhoneNumberAlreadyExistsException.class);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void countAnnouncers_ReturnsAnnouncersQuantity() throws Exception {
        when(announcerService.count()).thenReturn(1L);

        mockMvc.perform(get("/announcer/count"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").value(1L));
    }

    @Test
    public void updateAnnouncer_WithExistingId_ReturnsUpdatedAnnouncer() throws Exception {
        when(announcerService.update(1L, ANNOUNCER_DTO)).thenReturn(ANNOUNCER_DTO);

        mockMvc.perform(put("/announcer/" + 1).content(objectMapper.writeValueAsString(ANNOUNCER_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value(ANNOUNCER_DTO.name()))
                .andExpect(jsonPath("$.phoneNumber").value(ANNOUNCER_DTO.phoneNumber()));
    }

    @Test
    public void updateAnnouncer_WithUnexistingId_ReturnsNotFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(announcerService).update(1L, ANNOUNCER_DTO);

        mockMvc.perform(put("/announcer/" + 1).content(objectMapper.writeValueAsString(ANNOUNCER_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    public void listAnnouncers_ReturnsAnnouncers() throws Exception {
        when(announcerService.findAll()).thenReturn(ANNOUNCERS_DTO_LIST);

        mockMvc.perform(get("/announcer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deleteAnnouncer_WithExistingId_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/announcer/" + 1))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void deleteAnnouncer_WithUnexistingId_ReturnsNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(announcerService).delete(1L);

        mockMvc.perform(delete("/announcer" + 1))
                .andExpect(status().isNotFound());
    }

}
