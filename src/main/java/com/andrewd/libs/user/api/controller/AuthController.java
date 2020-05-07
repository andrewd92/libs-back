package com.andrewd.libs.user.api.controller;

import javax.validation.Valid;

import com.andrewd.libs.user.api.request.LoginUser;
import com.andrewd.libs.user.api.request.Registration;
import com.andrewd.libs.user.api.response.ResponseMessage;
import com.andrewd.libs.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity authenticateUser(@Valid @RequestBody LoginUser request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    ResponseEntity registerUser(@Valid @RequestBody Registration request) {

        try {
            authService.register(request);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseMessage(e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
