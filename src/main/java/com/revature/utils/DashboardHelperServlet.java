package com.revature.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DashboardHelperServlet {

    public String process(HttpServletRequest req) throws IOException {

        HttpSession session = req.getSession();
        Principal principal = (Principal) session.getAttribute("principal");
        String userRole = principal.getRole();

        switch (userRole) {
            case "ADMIN":
                return "admin.html";

            case "MANAGER":
                return "manager.html";

            case "EMPLOYEE":
                return "employee.html";

            default:
                return "badlogin.html";
        }


    }
}
