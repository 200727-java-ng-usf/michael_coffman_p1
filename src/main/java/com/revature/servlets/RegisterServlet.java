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

/**
 *  Servlet for registering new users to the web service and DB
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final AdminService adService = new AdminService();


    /**
     * Persists a newly registered user to the database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // I'm not typing it anymore.... I promise....
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        // Except this... I need the session user's ID to confirm they are of ADMIN privileges
        // We don't want a stranger to be able to make accounts from an open end-point
        HttpSession session = req.getSession();

        // Make sure only an admin can access this endpoint
        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);
        if (principal == null || !(principal.getRole().equalsIgnoreCase("ADMIN")) ) {
            resp.setStatus(401);
            throw new AuthenticationException();
        }

        try {
            // Maps the data from the request and saves the new user into the DB
            AppUser newUser = mapper.readValue(req.getInputStream(), AppUser.class);
            adService.register(newUser);
            resp.setStatus(201);

          // TODO Add custom exception handling
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
