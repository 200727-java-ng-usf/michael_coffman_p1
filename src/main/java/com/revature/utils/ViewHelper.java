package com.revature.utils;

import javax.servlet.http.HttpServletRequest;

public class ViewHelper {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/revabooks/login.view":
                return "partials/login.html";

            case "/revabooks/register.view":
                return "partials/register.html";

            default:
                return null;

        }
    }
}
