package com.revature.utils;

import javax.servlet.http.HttpServletRequest;

public class ViewHelper {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/project1/login.screen":
                return "partials/login.html";

            case "/project1/home.screen":
                return "partials/home.html";

            case "/project1/register.screen":
                return "partials/newUser.html";

            case "/project1/updateUser.screen":
                return "partials/updateUser.html";

            case "/project1/newReimburse.screen":
                return "partials/newReimburse.html";

            case "/project1/updateReimburse.screen":
                return "partials/updateReimburse.html";

            case "/project1/managerReimburse.screen":
                return "partials/managerReimburse.html";

            default:
                return null;

        }
    }
}
