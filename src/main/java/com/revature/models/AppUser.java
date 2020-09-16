package com.revature.models;

import java.util.Objects;

/**
 *  Generic user class. All users who interact with the system will be of AppUser type
 */
public class AppUser {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role;
    private int id;
    private String status;


    // Constructors
    public AppUser() {
        super();
    }

    public AppUser(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AppUser(String firstName, String lastName, String username, String password, String email, Role role, String status) {
        this(firstName, lastName, username, password, email);
        this.role = role;
        this.status = status;
    }

    public AppUser(String firstName, String lastName, String username, String password, String email, Role role, String status, int id) {
        this(firstName, lastName, username, password, email, role, status);
        this.id = id;
    }

    // Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Equals() and Hashcode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(email, appUser.email) &&
                role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, email, role, id, status);
    }

    // toString() method
    @Override
    public String toString() {
        return "AppUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
