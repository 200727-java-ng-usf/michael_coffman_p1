package com.revature.servlets;

import com.revature.utils.DashboardHelperServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dash")
public class DashboardMasterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String view = new DashboardHelperServlet().process(req);
        req.getRequestDispatcher(view).forward(req, resp);

    }
}