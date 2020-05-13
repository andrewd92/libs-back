package com.andrewd.libs.profile.service;

import java.util.List;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getById(long id) {
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile don't exist"));
    }

    public List<Profile> find(String query) {
        return profileRepository.findTop10ByUsernameContainsOrEmailContainsOrNameContains(query, query, query);
    }

    public void readingBookAdded(long userId) {
        Profile profile = profileRepository.findByUserId(userId);

        profile.newReadingStarted();

        profileRepository.save(profile);
    }
}
