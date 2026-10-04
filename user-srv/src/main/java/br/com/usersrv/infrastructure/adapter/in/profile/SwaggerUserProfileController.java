package br.com.usersrv.infrastructure.adapter.in.profile;

import br.com.usersrv.infrastructure.adapter.in.profile.dto.UserProfileResponse;
import br.com.usersrv.infrastructure.adapter.in.profile.dto.UserRegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PROFILE", description = "Endpoints for user profile")
@RequestMapping("/users")
public interface SwaggerUserProfileController {

    @Operation(summary = "Endpoint to get user profile by account id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{accountId}")
    ResponseEntity<UserProfileResponse> getProfileByAccountId(@PathVariable String accountId);

    @Operation(summary = "Endpoint to get user profile by other attributes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<UserProfileResponse>> getUsersProfiles(
            @RequestParam(name = "document", required = false) String document,
            @RequestParam(name = "name", required = false) String name
    );

    @Operation(summary = "Endpoint for user profile registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/registration")
    ResponseEntity<UserProfileResponse> register(@RequestBody UserRegistrationRequest request);
}
