package com.revature.servlets;

import com.revature.utils.DashboardHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/dash")
public class DashboardRouter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = new DashboardHelper().process(req);
        req.getRequestDispatcher(user).forward(req, resp);

    }

}
