package com.revature.servlets;

import com.revature.controllers.LoginController;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    public static String process(HttpServletRequest req) {

        switch(req.getRequestURI()) {
            case "/project1/html/admin.html":
                return LoginController.login(req);
            default:
                return "/html/splash.html";
        }
    }

}
