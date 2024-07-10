package com.lucasrznd.apigspot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.services.AnnouncerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lucasrznd.apigspot.common.AnnouncerConstants.ANNOUNCER_DTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        when(announcerService.insert(ANNOUNCER_DTO)).thenReturn(ANNOUNCER_DTO);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(ANNOUNCER_DTO));
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
        when(announcerService.insert(any())).thenThrow(NameAlreadyExistsException.class);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void createAnnouncer_WithExistingPhoneNumber_ReturnsConflict() throws Exception {
        when(announcerService.insert(any())).thenThrow(PhoneNumberAlreadyExistsException.class);

        mockMvc.perform(post("/announcer").content(objectMapper.writeValueAsString(ANNOUNCER_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

}
