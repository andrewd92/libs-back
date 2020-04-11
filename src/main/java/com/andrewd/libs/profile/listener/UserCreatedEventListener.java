package com.andrewd.libs.profile.listener;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.repository.ProfileRepository;
import com.andrewd.libs.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {

    private final ProfileRepository profileRepository;

    @Override
    public void onApplicationEvent(UserCreatedEvent event) {
        profileRepository.save(
                Profile.builder()
                    .userId(event.getId())
                    .name(event.getFullName())
                    .username(event.getUsername())
                    .email(event.getEmail())
                .build()
        );
    }
}
