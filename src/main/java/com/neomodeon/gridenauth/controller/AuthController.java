package com.neomodeon.gridenauth.controller;

import com.neomodeon.gridenauth.dto.AuthRequest;
import com.neomodeon.gridenauth.dto.AuthResponse;
import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;
import com.neomodeon.gridenauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("healthcheck")
    public ResponseEntity<String> healthcheck () {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> userRegister(@RequestBody UserRegisterRequest userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody AuthRequest userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody Map<String, String> request){
        return ResponseEntity.ok(userService.refreshToken(request));
    }
}
