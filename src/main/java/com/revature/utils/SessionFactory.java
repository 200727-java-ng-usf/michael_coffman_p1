package com.revature.utils;

import com.revature.controllers.LoginController;
import com.revature.services.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFactory extends HttpServlet{

    LoginService logService = new LoginService();
    LoginController logController = new LoginController();

    private String username;
    private String password;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {

            // Setting up the character information "text/html" references
            // the UTF-8 charset
            resp.setContentType("text/html");



            // Creating the session
            HttpSession session = req.getSession();

            session.setAttribute("firstName", logService.authenticate(logController.username, logController.password).getFirstName());
            session.setAttribute("lastName", logService.authenticate(logController.username, logController.password).getLastName());
            session.setAttribute("username", logService.authenticate(logController.username, logController.password).getUsername());
            session.setAttribute("password", logService.authenticate(logController.username, logController.password).getPassword());
            session.setAttribute("email", logService.authenticate(logController.username, logController.password).getEmail());
            session.setAttribute("id", logService.authenticate(logController.username, logController.password).getId());
            session.setAttribute("role", logService.authenticate(logController.username, logController.password).getRole());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
