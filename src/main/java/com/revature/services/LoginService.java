package com.revature.services;

import com.revature.DAO.LoginDAO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;

public class LoginService {

    private LoginDAO logDAO = new LoginDAO();

    public AppUser authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credentials, try again.");
        }

        AppUser authorizedUser = logDAO.findUserByLogin(username, password)
                                            .orElseThrow(AuthenticationException::new);

        return authorizedUser;
    }

}
