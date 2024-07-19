package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Tag(name = "SpotController", description = "Controller responsible for spots operations")
@RequestMapping("/spot")
public interface SpotController {

    @Operation(summary = "Save new spot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Spot created"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PostMapping
    ResponseEntity<SpotResponse> saveSpot(@RequestBody @Valid SpotDTO spotDTO);

    @Operation(summary = "Find all spots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spots found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SpotDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping
    ResponseEntity<List<SpotResponse>> findAll();

    @Operation(summary = "Returns quantity of spots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity found", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/count")
    ResponseEntity<Long> countSpots();

    @Operation(summary = "Returns amount raised of all registered spots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amount found", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/amount-raised")
    ResponseEntity<Double> getTotalAmountRaised();

    @Operation(summary = "Returns amount raised on current month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amount found", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("amount-raised-month")
    ResponseEntity<Double> getAmountRaisedCurrentMonth();

    @Operation(summary = "Calculate spot price based on duration and active contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculated price", content = @Content(
                    mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/calculate-price")
    ResponseEntity<Double> calculateSpotPrice(@Parameter(description = "Duration of spot", required = true, example = "0.45")
                                              @RequestParam Double duration,
                                              @Parameter(description = "Active contract", required = true, example = "true")
                                              @RequestParam boolean activeContract);

    @Operation(summary = "Returns the last five registered spots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculated price", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SpotDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/latest")
    ResponseEntity<List<SpotResponse>> findLatestSpots();

    @Operation(summary = "Returns a list of spots based on the parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find specific spots", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SpotDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @GetMapping("/search")
    ResponseEntity<List<SpotResponse>> searchSpot(@Parameter(description = "Initial date", required = true, example = "2024-07-01")
                                             @RequestParam LocalDate initialDate,
                                             @Parameter(description = "Final date", required = true, example = "2024-07-01")
                                             @RequestParam LocalDate finalDate,
                                             @Parameter(description = "Company name", example = "Magazine Luiza")
                                             @RequestParam(required = false) String companyName,
                                             @Parameter(description = "Announcer name", example = "Galvao Bueno")
                                             @RequestParam(required = false) String announcerName);

    @Operation(summary = "Update spot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spot updated", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SpotDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Spot not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @PutMapping("/{id}")
    ResponseEntity<SpotResponse> update(@Parameter(description = "Spot ID", required = true, example = "1")
                                        @PathVariable(name = "id") final Long id,
                                        @RequestBody @Valid SpotDTO spotDTO);

    @Operation(summary = "Delete spot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spot deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Spot not found", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StandardError.class))))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id") final Long id);

}
