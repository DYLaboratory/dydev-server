package com.dylabo.dydev.domain.auth.controller;

import com.dylabo.dydev.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-out")
    public ResponseEntity<String> doSignOut() {
        authService.signOut();
        return new ResponseEntity<>("Sign Out", HttpStatus.OK);
    }

}
