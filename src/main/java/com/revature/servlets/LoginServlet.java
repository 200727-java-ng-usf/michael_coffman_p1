package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Credentials;
import com.revature.dtos.ErrorResponse;
import com.revature.dtos.Principal;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;
import com.revature.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginService logService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // JSON -> JAVA language mapper
        ObjectMapper mapper = new ObjectMapper();
        // Writer to return responses back to server
        PrintWriter respWriter = resp.getWriter();
        // Set the content type of the incoming response.
        resp.setContentType("application/json");

        try {

            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);

            AppUser authUser = logService.authenticate(creds.getUsername(), creds.getPassword());
            Principal principal = new Principal(authUser);

            HttpSession session = req.getSession();
            session.setAttribute("principal", principal.stringify());

            String userJSON = mapper.writeValueAsString(principal);
            respWriter.write(userJSON);

            resp.setStatus(204);



//            switch (role) {
//                case "ADMIN":
//                    System.out.println("Admin User Detected");
//                    //resp.sendRedirect("/project1/admin.html");
//                    req.getRequestDispatcher("/admin").forward(req, resp);
//                    break;
//                case "MANAGER":
//                    //resp.sendRedirect("/project1/html/manager.html");
//                    break;
//                case "EMPLOYEE":
//                    //resp.sendRedirect("/project1/html/employee.html");
//                    break;
//                default:
//                    //resp.sendRedirect("/project1/html/badlogin.html");
//                    break;
//            }

        } catch (AuthenticationException | InvalidRequestException e) {

            resp.setStatus(400);
            ErrorResponse error = new ErrorResponse(400, "Bad credentials entered.");
            String errorJSON = mapper.writeValueAsString(error);
            respWriter.write(errorJSON);
        }
    }
}

