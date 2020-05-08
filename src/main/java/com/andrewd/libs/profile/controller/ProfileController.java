package com.andrewd.libs.profile.controller;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/v1")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public Profile getById(@PathVariable int id) {
        return profileService.getById(id);
    }
}
