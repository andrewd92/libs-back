package com.andrewd.libs.profile.service;

import java.util.Optional;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.repository.ProfileRepository;
import com.andrewd.libs.profile.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService service;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    public void getByIdShouldReturnProfileEntity() {
        Profile expectedProfile=  new Profile(1, 1, "Andrew", 1, 1, 1, 1);

         when(profileRepository.findById(expectedProfile.getId()))
                 .thenReturn(Optional.of(expectedProfile));

        Profile profile = service.getById(expectedProfile.getId());

        assertEquals(expectedProfile, profile);
    }

    @Test
    public void getByIdThrowExceptionWhenProfileNotExists() {
        long profileId = 1;

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(profileId));
    }
}