package com.revature.services;

import com.revature.DAO.LoginDAO;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;

import java.util.Optional;

public class LoginService {

    private LoginDAO logDAO = new LoginDAO();

    public Optional<AppUser> authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credentials, try again.");
        }

        Optional<AppUser> authorizedUser = logDAO.findUserByLogin(username, password);
                                            //.orElseThrow(AuthenticationException::new);

        return authorizedUser;
    }

}
