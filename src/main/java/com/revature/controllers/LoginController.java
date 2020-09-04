package com.revature.controllers;


import javax.servlet.http.HttpServletRequest;

public class LoginController {

    public static String username;
    public static String password;

    public static String login(HttpServletRequest req) {

        // Making sure method is a POST http method
        if(!req.getMethod().equals("POST")) {
            return "/html/splash.html";
        }

        // Get Login Info
        username = req.getParameter("username");
        password = req.getParameter("password");


        return null;
    }
}
