package alura.challenge.forohub.infrastructure.adapter.in.rest.controller;

import alura.challenge.forohub.application.exceptions.RegisteredUserException;
import alura.challenge.forohub.application.service.RegisterUserService;
import alura.challenge.forohub.infrastructure.adapter.in.rest.dto.RegisterNewUserDto;
import alura.challenge.forohub.infrastructure.adapter.in.rest.dto.RegisteredNewUserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserService registerUserService;

    @PostMapping
    @Operation(summary = "Creates a new user with unique email and password.")
    public ResponseEntity<RegisteredNewUserDto> registerNewUser(@RequestBody RegisterNewUserDto registerNewUserDto) throws RegisteredUserException {
        var registerdUser = registerUserService.registerNewUser(registerNewUserDto.toDomainModel());

        return ResponseEntity.ok(RegisteredNewUserDto.fromDomain(registerdUser));
    }
}
