package com.revature.models;

import java.util.Arrays;
import java.util.Optional;



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

    public static Optional<Status> getStatus(String name) {
        return Arrays.stream(Status.values())
                .filter(status -> status.statusName.equals(name))
                .findFirst();
    }

    @Override
    public String toString() {
        return statusName;
    }
}
