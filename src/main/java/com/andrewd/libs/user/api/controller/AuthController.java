package com.andrewd.libs.user.api.controller;

import javax.validation.Valid;

import java.util.Optional;

import com.andrewd.libs.user.api.response.JwtResponse;
import com.andrewd.libs.user.api.response.ResponseMessage;
import com.andrewd.libs.user.domain.User;
import com.andrewd.libs.user.repository.UserRepository;
import com.andrewd.libs.user.api.request.LoginUser;
import com.andrewd.libs.user.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1/")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    ResponseEntity authenticateUser(@Valid @RequestBody LoginUser request) {
        Optional<User> userCandidate = userRepository.findByUserName(request.getUsername());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(user.getUserName());

            return ResponseEntity.ok(new JwtResponse(jwt, user.getUserName(), user.getRole()));
        }

        return new ResponseEntity<>(new ResponseMessage("User not found!"), HttpStatus.BAD_REQUEST);
    }
}
