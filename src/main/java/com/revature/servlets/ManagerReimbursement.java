package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import com.revature.models.Reimbursements;
import com.revature.services.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TimeZone;

@WebServlet("/manager")
public class ManagerReimbursement extends HttpServlet {

    ManagerService manService = new ManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");

        try {
            // Retrieves all the reimbursements in the system that are pending only
            Set<Reimbursements> allReimbursements = manService.getAllReimbursements();
            String reimbursementsJSON = mapper.writeValueAsString(allReimbursements);
            respWriter.write(reimbursementsJSON);
            resp.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpSession session = req.getSession();
            PrintWriter respWriter = resp.getWriter();
            resp.setContentType("application/json");

            // For getting resolver Id
            String principalJSON = (String) req.getSession().getAttribute("principal");
            Principal principal = mapper.readValue(principalJSON, Principal.class);

            // Getting the requested reimbursements id and approval status
            Reimbursements reimbursement = mapper.readValue(req.getInputStream(), Reimbursements.class);

            // Making a new Reimbursement,  so all the fields are filled out by using the id from the above reimbursement
            Reimbursements reimbursementCurrent = manService.getReimbursementById(reimbursement.getId());

            // Assigning the resolver id and the new status to the pulled reimbursement from the DB
            reimbursementCurrent.setResolverId(principal.getId());
            reimbursementCurrent.setStatusId(reimbursement.getStatusId());

            // Setting up and assigning the time of resolve
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Timestamp time = Timestamp.valueOf(now.format(formatter));

            reimbursementCurrent.setResolved(time);
            System.out.println(reimbursementCurrent.getResolved());
            System.out.println(reimbursementCurrent.getSubmitted());

            manService.approveOrDeny(reimbursementCurrent);
            resp.setStatus(202);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
        }




    }
}
