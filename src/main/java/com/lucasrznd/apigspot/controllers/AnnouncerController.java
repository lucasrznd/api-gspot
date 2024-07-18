package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.exceptions.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Tag(name = "AnnouncerController", description = "Controller responsible for announcers operations")
@RequestMapping("/announcer")
public interface AnnouncerController {

    @Operation(summary = "Save new announcer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Announcer created"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PostMapping
    ResponseEntity<AnnouncerDTO> saveAnnouncer(@RequestBody @Valid final AnnouncerDTO announcerDTO);

    @Operation(summary = "Find all announcers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcers found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = AnnouncerDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping
    ResponseEntity<List<AnnouncerDTO>> findAll();

    @Operation(summary = "Returns quantity of announcers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity found", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/count")
    ResponseEntity<Long> countAnnouncers();

    @Operation(summary = "Update announcer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcer updated", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnnouncerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Announcer not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PutMapping("/{id}")
    ResponseEntity<AnnouncerDTO> update(@Parameter(description = "Announcer ID", required = true, example = "1")
                                        @PathVariable(name = "id") Long id,
                                        @RequestBody @Valid AnnouncerDTO announcerDTO);

    @Operation(summary = "Delete announcer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Announcer deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Announcer not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id") final Long id);

}
