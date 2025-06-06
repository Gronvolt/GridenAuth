package com.neomodeon.gridenauth.controller;

import com.neomodeon.gridenauth.dto.UserRegisterRequest;
import com.neomodeon.gridenauth.dto.UserRegisterResponse;
import com.neomodeon.gridenauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
