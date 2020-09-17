package com.revature.utils;

import javax.servlet.http.HttpServletRequest;

public class ViewHelper {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/login.screen":
                return "partials/login.html";

            case "/home.screen":
                return "partials/home.html";

            case "/register.screen":
                return "partials/newUser.html";

            case "/updateUser.screen":
                return "partials/updateUser.html";

            case "/newReimburse.screen":
                return "partials/newReimburse.html";

            case "/allReimbursements.screen":
                return "partials/allReimbursements.html";

            case "/updateReimburse.screen":
                return "partials/updateReimburse.html";

            case "/managerReimburse.screen":
                return "partials/managerReimburse.html";

            default:
                return null;

        }
    }
}
