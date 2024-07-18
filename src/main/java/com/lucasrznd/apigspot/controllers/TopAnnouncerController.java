package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.response.TopAnnouncerResponse;
import com.lucasrznd.apigspot.exceptions.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "TopAnnouncerControllerImpl", description = "Controller responsible for returning a list of top announcers in current month")
@RequestMapping("/top/announcers")
public interface TopAnnouncerController {

    @Operation(summary = "Find all top announcers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcers found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = TopAnnouncerResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping
    ResponseEntity<List<TopAnnouncerResponse>> findTopAnnouncersOnCurrentMonth();

}
