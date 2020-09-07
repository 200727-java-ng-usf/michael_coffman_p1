package com.revature.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  This controller collects form data and registers the user into our PostgreSQL database
 */
public class AdminAddUserController {

    public static String registerUser(HttpServletRequest req) throws IOException {

        //Get new user info
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");


        return null;
    }
}

