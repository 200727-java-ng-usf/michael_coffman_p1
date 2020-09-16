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

/**
 *  Responsible for updating a current user in the server's DB
 */
@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {

    private final AdminService adService = new AdminService();

    /**
     * Collects all the users in the server and responds with a JSON array to display in a table.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            ObjectMapper mapper = new ObjectMapper();
            PrintWriter respWriter = resp.getWriter();
            resp.setContentType("application/json");

            // Collect Set<AppUsers> for JSON String
            Set<AppUser> allUsers = adService.getAllUsers();

            // Maps the Set to a JSON String
            String usersJSON = mapper.writeValueAsString(allUsers);

            respWriter.write(usersJSON);
            resp.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(503);
        }

    }

    /**
     * Updates a user with new information filled out by the admin
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            ObjectMapper mapper = new ObjectMapper();
            PrintWriter respWriter = resp.getWriter();
            resp.setContentType("application/json");

            AppUser existingUser = mapper.readValue(req.getInputStream(), AppUser.class);

            System.out.println(existingUser);
            if (adService.updateUser(existingUser)) {
                resp.setStatus(202); // 1 or more rows were updated, so success
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(503);
        }



    }
}
