package com.revature.models;

import java.util.Arrays;
import java.util.Optional;


/**
 *  ENUM used for holding CONSTANTS of Reimbursement Types
 */
public enum Type {

    LODGING("Lodging"),
    TRAVEL("Travel"),
    FOOD("Food"),
    OTHER("Other");

    private String typeName;

    // Reimbursement Type constructor
    Type(String typeName) {
        this.typeName = typeName;
    }

    public static Optional<Type> getType(String name) {
        return Arrays.stream(Type.values())
                .filter(type -> type.typeName.equals(name))
                .findFirst();
    }

    @Override
    public String toString() {
        return typeName;
    }
}
