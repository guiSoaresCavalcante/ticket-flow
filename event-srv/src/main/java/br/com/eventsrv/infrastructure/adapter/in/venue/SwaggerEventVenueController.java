package br.com.eventsrv.infrastructure.adapter.in.venue;

import br.com.eventsrv.infrastructure.adapter.in.venue.dto.EventVenueRequest;
import br.com.eventsrv.infrastructure.adapter.in.venue.dto.EventVenueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "EVENT VENUE", description = "Endpoints for event venues")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/event-venue")
public interface SwaggerEventVenueController {

    @Operation(summary = "Endpoint to create an event venue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venue created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    ResponseEntity<EventVenueResponse> create(@RequestBody EventVenueRequest request);

    @Operation(summary = "Endpoint to list all event venues")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information returned successfully"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<EventVenueResponse>> findAll();
}
