package br.com.authsrv.infrastructure.adapter.in.signup;

import br.com.authsrv.infrastructure.adapter.in.signup.dto.SignUpRequest;
import br.com.authsrv.infrastructure.adapter.in.signup.dto.SignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "SIGNUP", description = "Application signup endpoints")
@RequestMapping("/auth")
public interface SwaggerSignUpController {

    @Operation(summary = "Endpoint for user signup, creating the authentication account and the user profile in user-srv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Signup successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request, username already registered or user-srv rejected the profile", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/signup")
    ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest request);
}
