package com.revature.utils;

import javax.servlet.http.HttpServletRequest;

public class ViewHelper {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/login.screen":
                return "/project1partials/login.html";

            case "/home.screen":
                return "/project1partials/home.html";

            case "/register.screen":
                return "/project1partials/newUser.html";

            case "/updateUser.screen":
                return "/project1partials/updateUser.html";

            case "/newReimburse.screen":
                return "/project1partials/newReimburse.html";

            case "/allReimbursements.screen":
                return "/project1partials/allReimbursements.html";

            case "/updateReimburse.screen":
                return "/project1partials/updateReimburse.html";

            case "/managerReimburse.screen":
                return "/project1/partials/managerReimburse.html";

            default:
                return null;

        }
    }
}
