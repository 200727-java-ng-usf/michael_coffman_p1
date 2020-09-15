package com.revature.models;

import java.util.Arrays;


/**
 *  ENUM used for holding CONSTANTS of Reimbursement Status
 */
public enum Status {

    PENDING("Pending"),
    APPROVED("Approved"),
    DENIED("Denied");

    private String statusName;

    // Reimbursement Status constructor
    Status(String statusName) {
        this.statusName = statusName;
    }

    public static Status getStatus(String name) {
        return Arrays.stream(Status.values())
                .filter(status -> status.statusName.equals(name))
                .findFirst()
                .orElse(PENDING);
    }

    @Override
    public String toString() {
        return statusName;
    }
}
