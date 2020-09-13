package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
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

        respWriter.write(usersJSON);

        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        AppUser existingUser = mapper.readValue(req.getInputStream(), AppUser.class);

        System.out.println(existingUser);
        if (adService.updateUser(existingUser)) {
            resp.setStatus(202); // 1 or more rows were updated, so success

        } else {
            resp.setStatus(400); // The updateUser method failed becuase all values matched
        }



    }
}
