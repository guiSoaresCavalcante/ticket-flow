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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "EVENT", description = "Endpoints for events")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/events")
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

    @Operation(summary = "Endpoint to find an event by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information returned successfully"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{eventId}")
    ResponseEntity<EventResponse> findById(@PathVariable UUID eventId);

    @Operation(summary = "Endpoint to attend an event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Attendance request published"),
            @ApiResponse(responseCode = "400", description = "Invalid token profile", content = @Content),
            @ApiResponse(responseCode = "401", description = "Missing or invalid token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Message broker unavailable", content = @Content)
    })
    @PostMapping("/{eventId}/attend")
    ResponseEntity<Void> attend(@PathVariable UUID eventId,
                                @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);
}
