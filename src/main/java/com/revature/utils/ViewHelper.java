package com.revature.utils;

import javax.servlet.http.HttpServletRequest;

public class ViewHelper {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/project1/login.screen":
                return "partials/login.html";

            case "/project1/admin.screen":
                return "partials/admin.html";

            default:
                return null;

        }
    }
}
