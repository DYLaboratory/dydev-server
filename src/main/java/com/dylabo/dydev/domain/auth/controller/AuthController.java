package com.dylabo.dydev.domain.auth.controller;

import com.dylabo.dydev.common.session.SessionDto;
import com.dylabo.dydev.domain.auth.service.AuthService;
import com.dylabo.dydev.domain.auth.service.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<SessionDto> doSignIn(@Validated @RequestBody AuthDto authDto) {
        return new ResponseEntity<>(authService.signIn(authDto), HttpStatus.OK);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> doSignOut() {
        return new ResponseEntity<>(authService.signOut(), HttpStatus.OK);
    }

}
