package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

public class AdminController {

    public static String process(HttpServletRequest req) {

        // Making sure method IS a POST method coming from login form
        if (req.getMethod().equals("POST")) {
            return "/html/admin.html";
        }


        return null;
    }
}
