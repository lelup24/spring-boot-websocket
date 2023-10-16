package de.kipper.websocket.configs.security;

import de.kipper.websocket.data.tables.daos.UserEntityDao;
import java.util.List;
import java.util.Optional;

import de.kipper.websocket.data.tables.pojos.Role;
import de.kipper.websocket.data.tables.pojos.UserEntity;
import de.kipper.websocket.user.UserFinder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserEntityDao userEntityDao;
    private final UserFinder userFinder;

    public UserDetailsService(final UserEntityDao userEntityDao, final UserFinder userFinder) {
        this.userEntityDao = userEntityDao;
        this.userFinder = userFinder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Optional<UserEntity> userEntityOptional = userEntityDao.fetchOptionalByUsername(username);

        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        final UserEntity userEntity = userEntityOptional.get();
        final List<Role> roles = userFinder.findRolesByUserId(userEntity.getId());

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles.stream().map(Role::getName).toArray(String[]::new))
                .build();
    }
}