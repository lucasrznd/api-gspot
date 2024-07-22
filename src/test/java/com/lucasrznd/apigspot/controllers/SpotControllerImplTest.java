package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.controllers.impl.SpotControllerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrznd.apigspot.services.SpotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lucasrznd.apigspot.common.SpotConstants.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

}
