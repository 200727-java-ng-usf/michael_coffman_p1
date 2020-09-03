package com.revature.models;

import java.util.Arrays;
import java.util.Optional;



/**
 *  ENUM used for holding CONSTANTS of User Roles
 */
public enum Role {

    ADMIN("Admin"),
    MANAGER("Manager"),
    EMPLOYEE("Employee");

    private String roleName;

    // Role constructor
    Role(String roleName) {
        this.roleName = roleName;
    }

    public static Optional<Role> getRoleName(String name) {
        return Arrays.stream(Role.values())
                .filter(role -> role.roleName.equals(name))
                .findFirst();
    }

    @Override
    public String toString() {
        return roleName;
    }
}
