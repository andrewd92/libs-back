package com.andrewd.libs.profile.repository;

import com.andrewd.libs.profile.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
