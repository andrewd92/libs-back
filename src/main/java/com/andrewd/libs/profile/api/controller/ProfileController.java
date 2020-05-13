package com.andrewd.libs.profile.api.controller;

import javax.validation.Valid;

import java.util.List;

import com.andrewd.libs.profile.api.request.UserSearch;
import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/v1")
@RequiredArgsConstructor
@CrossOrigin()
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public Profile getById(@PathVariable int id) {
        return profileService.getById(id);
    }

    @PostMapping("/find/")
    public List<Profile> find(@Valid @RequestBody UserSearch request) {
        return profileService.find(request.getQuery());
    }
}
