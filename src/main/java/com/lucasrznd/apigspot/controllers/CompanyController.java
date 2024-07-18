package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
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

@Tag(name = "CompanyController", description = "Controller responsible for companies operations")
@RequestMapping("/company")
public interface CompanyController {

    @Operation(summary = "Save new company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PostMapping
    ResponseEntity<CompanyDTO> saveCompany(@RequestBody @Valid final CompanyDTO companyDTO);

    @Operation(summary = "Find all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping
    ResponseEntity<List<CompanyDTO>> findAll();

    @Operation(summary = "Returns quantity of companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity found", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/count")
    ResponseEntity<Long> countCompanies();

    @Operation(summary = "Update company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company updated", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PutMapping("/{id}")
    ResponseEntity<CompanyDTO> update(@Parameter(description = "Company ID", required = true, example = "1")
                                      @PathVariable(name = "id") Long id,
                                      @RequestBody @Valid CompanyDTO companyDTO);

    @Operation(summary = "Delete company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Company deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id") final Long id);

}
