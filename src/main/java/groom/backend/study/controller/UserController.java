package groom.backend.study.controller;

import groom.backend.study.dto.UserRegisterDto;
import groom.backend.study.dto.UserLoginDto;
import groom.backend.study.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDto registerDto) {
        userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDto loginDto) {
        String token = userService.loginUser(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
