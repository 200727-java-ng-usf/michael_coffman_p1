package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursements {

    private int id; // will be generated by db
    private double amount; // emp-entered field
    private Timestamp submitted; // will be generated by db
    private Timestamp resolved; // will be generated by db
    private String description; // emp-entered field
    private int authorId; // emp-assigned when passed to servlet with principal
    private int resolverId; // assigned when a manager resolves reimbursement
    private Status statusId; // will be PENDING by default, updated by manager
    private Type typeId; // emp-entered field
    private String resolverName; // for displaying resolver in table
    private String authorName; // for displaying author in table

    public Reimbursements() {
    }

    // For reimbursement submitted
    public Reimbursements(double amount, String description, int authorId, Type typeId) {
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    // For reimbursement viewing / updating
    public Reimbursements(int id, double amount, Timestamp submitted, Timestamp resolved, String description, int authorId, int resolverId, Status statusId, Type typeId, String authorName, String resolverName) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    public String getResolverName() {
        return resolverName;
    }

    public void setResolverName(String resolverName) {
        this.resolverName = resolverName;
    }

    public String getAuthoName() {
        return authorName;
    }

    public void setAuthoName(String authoName) {
        this.authorName = authoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursements that = (Reimbursements) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                authorId == that.authorId &&
                resolverId == that.resolverId &&
                Objects.equals(submitted, that.submitted) &&
                Objects.equals(resolved, that.resolved) &&
                Objects.equals(description, that.description) &&
                statusId == that.statusId &&
                typeId == that.typeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitted, resolved, description, authorId, resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }
}
