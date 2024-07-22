package com.lucasrznd.apigspot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrznd.apigspot.controllers.impl.SpotControllerImpl;
import com.lucasrznd.apigspot.exceptions.ResourceNotFoundException;
import com.lucasrznd.apigspot.services.SpotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lucasrznd.apigspot.common.SpotConstants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpotControllerImpl.class)
public class SpotControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SpotService service;

    @Test
    public void createSpot_WithValidData_ReturnsCreated() throws Exception {
        when(service.save(SPOT_DTO)).thenReturn(SPOT_RESPONSE);

        mockMvc.perform(post("/spot").content(objectMapper.writeValueAsString(SPOT_DTO))
                        .contentType(APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(SPOT_RESPONSE.title()));
    }

    @Test
    public void createSpot_WithInvalidData_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/spot").content(objectMapper.writeValueAsString(NULL_SPOT_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

        mockMvc.perform(post("/spot").content(objectMapper.writeValueAsString(INVALID_SPOT_DTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void getTotalAmountRaised_ReturnsAmount() throws Exception {
        when(service.getTotalAmountRaised()).thenReturn(1000D);

        mockMvc.perform(get("/spot/amount-raised"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1000));
    }

    @Test
    public void getAmountRaisedCurrentMonth_ReturnsAmount() throws Exception {
        when(service.getAmountRaisedCurrentMonth()).thenReturn(500D);

        mockMvc.perform(get("/spot/amount-raised-month"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(500));
    }

    @Test
    public void countSpots_ReturnsSpotsQuantity() throws Exception {
        when(service.count()).thenReturn(1L);

        mockMvc.perform(get("/spot/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    public void updateSpot_WithExistingId_ReturnsUpdatedSpot() throws Exception {
        when(service.update(1L, SPOT_DTO)).thenReturn(SPOT_RESPONSE);

        mockMvc.perform(put("/spot/" + 1).content(objectMapper.writeValueAsString(SPOT_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(SPOT_DTO.title()))
                .andExpect(jsonPath("$.duration").value(SPOT_DTO.duration()));
    }

    @Test
    public void updateSpot_WithUnexistingId_ReturnsNotFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(service).update(1L, SPOT_DTO);

        mockMvc.perform(put("/spot/" + 1).content(objectMapper.writeValueAsString(SPOT_DTO)).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    public void listSpots_ReturnsSpots() throws Exception {
        when(service.findAll()).thenReturn(SPOT_RESPONSE_LIST);

        mockMvc.perform(get("/spot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
