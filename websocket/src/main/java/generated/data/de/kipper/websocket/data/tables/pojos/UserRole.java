/*
 * This file is generated by jOOQ.
 */
package de.kipper.websocket.data.tables.pojos;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private UUID roleId;

    public UserRole() {}

    public UserRole(UserRole value) {
        this.userId = value.userId;
        this.roleId = value.roleId;
    }

    public UserRole(
        UUID userId,
        UUID roleId
    ) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * Getter for <code>public.user_role.user_id</code>.
     */
    @NotNull
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.user_role.user_id</code>.
     */
    public UserRole setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Getter for <code>public.user_role.role_id</code>.
     */
    @NotNull
    public UUID getRoleId() {
        return this.roleId;
    }

    /**
     * Setter for <code>public.user_role.role_id</code>.
     */
    public UserRole setRoleId(UUID roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserRole other = (UserRole) obj;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.roleId == null) {
            if (other.roleId != null)
                return false;
        }
        else if (!this.roleId.equals(other.roleId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserRole (");

        sb.append(userId);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
    }
}
