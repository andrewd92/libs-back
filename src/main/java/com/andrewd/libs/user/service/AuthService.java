package com.andrewd.libs.user.service;

import java.util.Optional;

import com.andrewd.libs.user.api.request.Registration;
import com.andrewd.libs.user.domain.Role;
import com.andrewd.libs.user.domain.User;
import com.andrewd.libs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Boolean register(Registration request) {
        Optional<User> userCandidate = userRepository.findByUserName(request.getUsername());

        if (!userCandidate.isPresent()) {
            User user = User.builder()
                    .userName(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_USER)
                    .build();

            userRepository.save(user);

            return true;
        }

        return false;
    }
}
