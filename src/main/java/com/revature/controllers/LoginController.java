package com.revature.controllers;


import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginController {


    private static LoginService logService = new LoginService();

    public static String login(HttpServletRequest req) {

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
                    return "/html/admin/adminmain.html";
                case MANAGER:
                    return "/html/manager/managermain.html";
                case EMPLOYEE:
                    return "/html/employee/employeemain.html";
            }
        }

        return "/html/badlogin.html";

    }
}

