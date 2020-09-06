package com.revature.servlets;

import com.revature.controllers.LoginController;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    public static String process(HttpServletRequest req) {

        switch(req.getRequestURI()) {
            case "/project1/login":
                return LoginController.login(req);
            default:
                //TODO Implement custom bad URI request page to redirect to
                return "/project1/login";
        }

    }

}
