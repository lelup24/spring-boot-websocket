package de.kipper.websocket.user;

import static de.kipper.websocket.data.tables.Role.ROLE;
import static de.kipper.websocket.data.tables.UserRole.USER_ROLE;

import java.util.List;
import java.util.UUID;

import de.kipper.websocket.data.tables.pojos.Role;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
public class UserFinder {

  private final DSLContext dsl;

  public UserFinder(final DSLContext dsl) {
    this.dsl = dsl;
  }

  public List<Role> findRolesByUserId(UUID userId) {
    return dsl.select(ROLE.asterisk())
        .from(ROLE)
        .join(USER_ROLE)
        .on(ROLE.ID.eq(USER_ROLE.ROLE_ID))
        .where(USER_ROLE.USER_ID.eq(userId))
        .fetchInto(Role.class);
  }
}
