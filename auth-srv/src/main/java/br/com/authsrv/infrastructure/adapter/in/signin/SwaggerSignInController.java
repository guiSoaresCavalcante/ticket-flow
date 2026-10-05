package br.com.authsrv.infrastructure.adapter.in.signin;

import br.com.authsrv.infrastructure.adapter.in.signin.dto.SignInRequest;
import br.com.authsrv.infrastructure.adapter.in.signin.dto.SignInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "SIGNIN", description = "Application signin endpoints")
@RequestMapping("/auth")
public interface SwaggerSignInController {

    @Operation(summary = "Endpoint for user signin, returning a JWT to be used across ticket-flow services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signin successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/signin")
    ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request);
}
