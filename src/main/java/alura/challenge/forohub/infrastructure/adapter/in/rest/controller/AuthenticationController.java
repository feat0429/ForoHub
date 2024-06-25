package alura.challenge.forohub.infrastructure.adapter.in.rest.controller;

import alura.challenge.forohub.application.service.TokenService;
import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.infrastructure.adapter.in.rest.dto.AuthenticateUserDto;
import alura.challenge.forohub.infrastructure.adapter.in.rest.dto.JwtTokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    @Operation(summary = "Get token for authenticated user given a valid email and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful user authentication.",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = JwtTokenDto.class))
            )
    })
    public ResponseEntity<JwtTokenDto> authenticateUser(@RequestBody AuthenticateUserDto authenticateUserDto){
        Authentication token = new UsernamePasswordAuthenticationToken(
                authenticateUserDto.email(),
                authenticateUserDto.password()
        );

        var authenticatedUser = authenticationManager.authenticate(token);

        var jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDto(jwtToken));
    }
}
