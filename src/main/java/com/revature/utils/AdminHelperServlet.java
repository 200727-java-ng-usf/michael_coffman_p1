package com.revature.utils;


import javax.servlet.http.HttpServletRequest;

public class AdminHelperServlet {

    public String process(HttpServletRequest req) {

        switch (req.getRequestURI()) {

            case "/project1/dash":
                return "html/admin.html";

            default:
                return null;




        }
    }
}
