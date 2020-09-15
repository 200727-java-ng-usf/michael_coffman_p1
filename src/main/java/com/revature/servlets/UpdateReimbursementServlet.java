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

@WebServlet("/updateReimburse")
public class UpdateReimbursementServlet extends HttpServlet {

    private final EmployeeService empService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        Set<Reimbursements> usersReimbursements = empService.getUsersReimbursements(principal);
        String reimbursementJSON = mapper.writeValueAsString(usersReimbursements);


        respWriter.write(reimbursementJSON);

        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        String principalJSON = (String) req.getSession().getAttribute("principal");
        Principal principal = mapper.readValue(principalJSON, Principal.class);

        Reimbursements updateReimburse = mapper.readValue(req.getInputStream(), Reimbursements.class);

        updateReimburse.setAuthorId(principal.getId());
        System.out.println(updateReimburse);

        if (empService.updateReimbursement(updateReimburse)) {
            resp.setStatus(202);
        } else {
            resp.setStatus(400);
        }
    }
}
