package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Determines the session user's Id, and responds with the correct configuration int so
 * each user's role has a different home page.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    /**
     * 'GET's the session user's role to help designate how the home page configuration will load.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        HttpSession session = req.getSession();

        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        String role = principal.getRole();

        switch (role) {

            case "ADMIN":
                respWriter.write("1");
                break;
            case "MANAGER":
                respWriter.write("2");
                break;
            case "EMPLOYEE":
                respWriter.write("3");
                break;
        }




    }
}
