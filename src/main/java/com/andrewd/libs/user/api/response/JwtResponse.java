package com.andrewd.libs.user.api.response;

import java.util.Collection;

import com.andrewd.libs.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;

    private String username;

    private Role role;
}
