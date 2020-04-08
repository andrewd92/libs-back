package com.andrewd.libs.profile.repository;

import com.andrewd.libs.profile.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
