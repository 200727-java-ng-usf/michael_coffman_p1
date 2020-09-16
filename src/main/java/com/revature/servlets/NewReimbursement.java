package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import com.revature.models.Reimbursements;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  Creates a new Reimbursement to store in the server DB
 */
@WebServlet("/newReimburse")
public class NewReimbursement extends HttpServlet {

    private final EmployeeService empService = new EmployeeService();

    /**
     * Creates new Reimbursement using JSON form data and the session user's Id
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");
        HttpSession session = req.getSession();

        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        Reimbursements newReimburse = mapper.readValue(req.getInputStream(), Reimbursements.class);
        newReimburse.setAuthorId(principal.getId());

        if (empService.submitReimburse(newReimburse)) {
            resp.setStatus(201);
        } else {
            resp.setStatus(400);
        }



    }
}
