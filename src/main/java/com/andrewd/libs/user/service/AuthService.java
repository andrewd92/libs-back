package com.andrewd.libs.user.service;

import java.util.Optional;

import com.andrewd.libs.user.api.request.Registration;
import com.andrewd.libs.user.domain.Role;
import com.andrewd.libs.user.domain.User;
import com.andrewd.libs.user.event.UserCreatedEvent;
import com.andrewd.libs.user.exception.UserAlreadyExists;
import com.andrewd.libs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

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
}
