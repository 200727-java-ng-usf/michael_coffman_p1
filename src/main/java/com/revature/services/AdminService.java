package com.revature.services;

import com.revature.DAO.AdminDAO;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;

import java.util.Optional;
import java.util.Set;

/**
 *  Encapsulates all the methods that an ADMIN user has access to.
 */
public class AdminService {

    private AdminDAO adDAO = new AdminDAO();


    /**
     * Registers a new user into the server
     * @param newUser
     */
    public void register(AppUser newUser) {

        // checking for valid credentials entered
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

        // checking to see if username and email are already in use
        Optional<AppUser> existingUser = adDAO.findUserByUsername(newUser.getUsername());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Provided username is already in use!");
        }

        adDAO.save(newUser);
    }

    /**
     * Retrieves every user stored in the server.
     * @return Set<AppUser>
     */
    public Set<AppUser> getAllUsers() {

        Set<AppUser> users = adDAO.getAllUsers();

        return users;
    }

    /**
     * Updates the target user specified by an HttpRequest and returns a boolean for confirmation
     * @param existingUser
     * @return boolean
     */
    public boolean updateUser(AppUser existingUser) {
        if (adDAO.updateUser(existingUser)) {
            return true;
        }
        return false;
    }

    /**
     *  Checks to see if the credentials make sense, no null or spaced values
     * @param user
     * @return boolean
     */
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

}
