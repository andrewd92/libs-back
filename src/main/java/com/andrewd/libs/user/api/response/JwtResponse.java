package com.andrewd.libs.user.api.response;

import com.andrewd.libs.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;

    private Long userId;

    private String username;

    private Role role;
}
