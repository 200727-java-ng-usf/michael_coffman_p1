package com.revature.utils;


import javax.servlet.http.HttpServletRequest;

public class AdminHelperServlet {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/login":
                System.out.println("you should be on the admin dashboard");
                return "admin.html";

            default:
                return "admin.html";




        }
    }
}
