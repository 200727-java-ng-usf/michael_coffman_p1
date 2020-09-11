package com.revature.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DashboardHelperServlet {

    public String process(HttpServletRequest req) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        HttpSession session = req.getSession();

        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);
        String userRole = principal.getRole();

        switch (userRole) {
            case "ADMIN":
                System.out.println("redirecting to admin.html");
                return "/admin.html";

            case "MANAGER":
                return "/manager.html";

            case "EMPLOYEE":
                return "/employee.html";

            default:
                return "/badlogin.html";
        }


    }
}
