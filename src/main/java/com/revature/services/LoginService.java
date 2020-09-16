package com.revature.services;

import com.revature.DAO.LoginDAO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;

import java.util.Optional;

/**
 *  Authenticates a user to start a session on the server.
 */
public class LoginService {

    private LoginDAO logDAO = new LoginDAO();


    /**
     * Checks to see if the user exists in the server
     * @param username
     * @param password
     * @return AppUser
     */
    public AppUser authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        return logDAO.findUserByLogin(username, password)
                .orElseThrow(AuthenticationException::new);
    }

}
