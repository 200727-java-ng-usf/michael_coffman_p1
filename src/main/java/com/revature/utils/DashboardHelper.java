package com.revature.utils;

import com.revature.dtos.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DashboardHelper {

    public String process(HttpServletRequest req) {

        HttpSession session = req.getSession();

        Principal principal = (Principal) session.getAttribute("principal");

        String role = principal.getRole();

        switch (role) {
            case "ADMIN":
                return "partials/admin/admin.screen";
            default:
                return "partials/badlogin.html";
        }






    }
}
