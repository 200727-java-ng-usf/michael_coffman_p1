package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import com.revature.models.AppUser;
import com.revature.models.Reimbursements;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TimeZone;

/**
 *  Servlet used for processing HTTPRequests involving the updating of user reimbursements
 */
@WebServlet("/updateReimburse")
public class UpdateReimbursementServlet extends HttpServlet {

    private final EmployeeService empService = new EmployeeService();

    /**
     * Takes in an HTTPRequest and reponds with the current user's Set<> of reimbursements
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // JSON <---> Java
        ObjectMapper mapper = new ObjectMapper();
        // For writing HttpResponses back to the server
        PrintWriter respWriter = resp.getWriter();
        // Setting the response type for my HttpResponse
        resp.setContentType("application/json");

        // Getting the current session's user to make sure only to retrieve their reimbursements
        // Don't want everyone having a looky-doo at your stuff
        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        // Running the getUsersReimbursements from EmployeeServices to get a Set<Reimbursements> and
        // map to JSON
        Set<Reimbursements> usersReimbursements = empService.getPendingReimbursements(principal);
        String reimbursementJSON = mapper.writeValueAsString(usersReimbursements);

        // Responding to the server with the JSON String
        respWriter.write(reimbursementJSON);

        // Setting the response status to 200 = OK (just retrieving data)
        resp.setStatus(200);
    }

    /**
     * Handles HttpRequest for updating a user's reimbursement
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //TODO Make sure only PENDING reimbursements can be changed unless it's by a manager

        // JSON <---> Java, Response Writer, Response Content Type
        // This is the last I'm typing this out for these 3 lines of code...
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        // Getting session user and assigning to Principal class;
        // This helps ensure that when they submit their update, I have
        // and extra Id to enforce them not changing multiple records in the DB
        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        // Makes the reimbursement object for updating
        Reimbursements updateReimburse = mapper.readValue(req.getInputStream(), Reimbursements.class);

        // Reinforces that this updated reimbursement belongs to the current session user
        // Before I did this, it was changing all of the user's reimbursements, so I
        // combined this as part of the WHERE statement with AND to ensure proper transactions
        updateReimburse.setAuthorId(principal.getId());

        //System.out.println(updateReimburse);

        // If the the reimbursement updates, a row is updated and returns a boolean
        // all the way back here.
        // TODO add custom error/exception handling to respWriter
        if (empService.updateReimbursement(updateReimburse)) {
            resp.setStatus(202);
        } else {
            resp.setStatus(400);
        }
    }
}
