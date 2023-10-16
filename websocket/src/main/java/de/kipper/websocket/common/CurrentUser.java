package de.kipper.websocket.common;

import java.util.UUID;

import de.kipper.websocket.data.tables.daos.UserEntityDao;
import de.kipper.websocket.data.tables.pojos.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUser {

    private final UserEntityDao userEntityDao;

    public CurrentUser(final UserEntityDao userEntityDao) {
        this.userEntityDao = userEntityDao;
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserEntity getUser() {
        final String username = getUsername();
        return userEntityDao
                .fetchOptionalByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name [" + username + "]"));
    }

    public UUID getId() {
        return getUser().getId();
    }
}
