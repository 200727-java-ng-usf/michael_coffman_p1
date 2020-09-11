package com.revature.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AppUser;

import java.util.Objects;

public class Principal {

    private String username;
    private String role;
    private int id;

    public Principal() {
        super();
    }

    public Principal(AppUser user) {
        this.username = user.getUsername();
        this.role = user.getRole().toString();
        this.id = user.getId();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principal principal = (Principal) o;
        return id == principal.id &&
                Objects.equals(username, principal.username) &&
                Objects.equals(role, principal.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role, id);
    }

    @Override
    public String toString() {
        return "Principal{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                '}';
    }

    public String stringify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
