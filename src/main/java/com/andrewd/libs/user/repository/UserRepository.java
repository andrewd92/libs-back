package com.andrewd.libs.user.repository;

import java.util.Optional;

import com.andrewd.libs.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
