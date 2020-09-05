package com.revature.controllers;


import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.services.LoginService;

import javax.servlet.http.HttpServletRequest;

public class LoginController {


    private static LoginService logService = new LoginService();

    public static String login(HttpServletRequest req) {

        // Making sure method is a POST http method
        if (!req.getMethod().equals("post")) {
            return "/html/login.html";
        }

        // Get Login Info
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AppUser loggedUser = logService.authenticate(username, password);


        if (loggedUser != null) {

            Role role = loggedUser.getRole();
            username = loggedUser.getUsername();

            req.getSession().setAttribute("Name", username);
            req.getSession().setAttribute("Role", role);

            switch(role) {
                case ADMIN:
                    return "/html/admin.html";
                case MANAGER:
                    return "/api/manager";
                case EMPLOYEE:
                    return "/api/employee";
            }
        }

        return "/html/badlogin.html";


    }
}

