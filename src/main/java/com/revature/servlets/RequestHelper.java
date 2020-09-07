package com.revature.servlets;

import com.revature.controllers.LoginController;
import com.revature.controllers.admin.AdminAddUserController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestHelper {

    public static String process(HttpServletRequest req) throws IOException {

        switch(req.getRequestURI()) {
            case "/project1/api/login":
                return LoginController.login(req);


            case "/project1/api/newuser":
                return AdminAddUserController.registerUser(req);


            default:
                //TODO Implement custom bad URI request page to redirect to
                return "/project1/login";
        }

    }

}
