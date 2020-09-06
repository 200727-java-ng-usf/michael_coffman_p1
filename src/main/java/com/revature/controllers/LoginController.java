package com.revature.controllers;


import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginController {


    private static LoginService logService = new LoginService();

    public static String login(HttpServletRequest req) {

        // Making sure method isn't a POST http method
        if (!req.getMethod().equals("POST")) {
            return "/html/login.html";
        }

        // Get Login Info
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<AppUser> loggedUser = logService.authenticate(username, password);


        if (loggedUser.isPresent()) {

            Role role = loggedUser.get().getRole();
            username = loggedUser.get().getUsername();

            req.getSession().setAttribute("Name", username);
            req.getSession().setAttribute("Role", role);

            switch(role) {
                case ADMIN:
                    return "/html/admin.html";
                case MANAGER:
                    return "/html/manager.html";
                case EMPLOYEE:
                    return "/html/employee.html";
            }
        }

        return "/html/badlogin.html";

    }
}

