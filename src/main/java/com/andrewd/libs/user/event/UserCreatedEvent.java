package com.andrewd.libs.user.event;

import com.andrewd.libs.user.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {

    private long id;

    private String username;

    private String email;

    private String fullName;

    public UserCreatedEvent(Object source, User user, String fullName) {
        super(source);
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.fullName = fullName;
    }
}
