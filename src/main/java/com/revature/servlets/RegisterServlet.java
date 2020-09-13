package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import com.revature.exceptions.AuthenticationException;
import com.revature.models.AppUser;
import com.revature.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final AdminService adService = new AdminService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        HttpSession session = req.getSession();
        resp.setContentType("application/json");

        //make sure only an admin can access this endpoint
        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);
        if (principal == null || !(principal.getRole().equalsIgnoreCase("ADMIN")) ) {
            resp.setStatus(401);
            throw new AuthenticationException();
        }

        try {

            AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
            adService.register(newUser);
            resp.setStatus(201);

          // TODO Add custom exception handling
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
