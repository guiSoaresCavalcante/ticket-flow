package br.com.eventsrv.infrastructure.adapter.in.event;

import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventRequest;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventResponse;
import br.com.eventsrv.application.domain.auth.entity.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "EVENT", description = "Endpoints for events")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/event")
public interface SwaggerEventController {

    @Operation(summary = "Endpoint to create an event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    ResponseEntity<EventResponse> create(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user,
                                         @RequestBody EventRequest request);

    @Operation(summary = "Endpoint to list all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information returned successfully"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<EventResponse>> findAll();
}
