package com.revature.models;

import java.util.Arrays;


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

    /**
     *  Sorts through JDBC ResultSet for returning Type
     * @param name
     * @return Type
     */
    public static Type getType(String name) {
        return Arrays.stream(Type.values())
                .filter(type -> type.typeName.equalsIgnoreCase((name)))
                .findFirst()
                .orElse(OTHER);
    }

    @Override
    public String toString() {
        return typeName;
    }
}
