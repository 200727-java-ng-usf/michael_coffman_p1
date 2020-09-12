package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AppUser;
import com.revature.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {

    private final AdminService adService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        Set<AppUser> allUsers = adService.getAllUsers();

        String usersJSON = mapper.writeValueAsString(allUsers);

        // prints to terminal allUsers in JSON format
        System.out.println(usersJSON);

        // when console.log in JS, it says empty
        respWriter.write(usersJSON);

        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {





    }
}
