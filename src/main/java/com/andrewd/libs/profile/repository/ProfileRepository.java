package com.andrewd.libs.profile.repository;

import java.util.List;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findTop10ByUsernameContainsOrEmailContainsOrNameContains(String userName, String email, String name);
}
