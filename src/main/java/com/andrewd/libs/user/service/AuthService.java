package com.andrewd.libs.user.service;

import java.util.Optional;

import com.andrewd.libs.user.api.request.LoginUser;
import com.andrewd.libs.user.api.request.Registration;
import com.andrewd.libs.user.api.response.JwtResponse;
import com.andrewd.libs.user.domain.Role;
import com.andrewd.libs.user.domain.User;
import com.andrewd.libs.user.event.UserCreatedEvent;
import com.andrewd.libs.user.exception.UserAlreadyExists;
import com.andrewd.libs.user.exception.UserNotFound;
import com.andrewd.libs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final AuthenticationManager authenticationManager;

    public void register(Registration request) {
        Optional<User> userCandidate = userRepository.findByUserName(request.getUsername());

        if (userCandidate.isPresent()) {
            throw new UserAlreadyExists();
        }

        User user = User.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        applicationEventPublisher.publishEvent(
                new UserCreatedEvent(this, user, request.getFullName())
        );
    }

    public JwtResponse login(LoginUser request) {
        Optional<User> userCandidate = userRepository.findByUserName(request.getUsername());

        if (!userCandidate.isPresent()) {
            throw new UserNotFound();
        }

        User user = userCandidate.get();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(user.getUserName());

        return new JwtResponse(jwt, user.getId(), user.getUserName(), user.getRole());
    }
}
