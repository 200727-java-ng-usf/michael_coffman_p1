package com.revature.models;

import java.util.Arrays;
import java.util.Optional;



/**
 *  ENUM used for holding CONSTANTS of User Roles
 */
public enum Role {

    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE");

    private String roleName;

    // Role constructor
    Role(String roleName) {
        this.roleName = roleName;
    }

    public static Role getRoleName(String name) {
        return Arrays.stream(Role.values())
                .filter(role -> role.roleName.equals(name))
                .findFirst()
                .orElse(EMPLOYEE);
    }

    @Override
    public String toString() {
        return roleName;
    }
}
