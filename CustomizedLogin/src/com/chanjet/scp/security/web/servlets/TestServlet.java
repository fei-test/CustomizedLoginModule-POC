package com.chanjet.scp.security.web.servlets;

import com.chanjet.scp.security.web.authorization.CSPUserPrincipal;
import com.chanjet.scp.security.web.authorization.UserPrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by feishan on 10/17/14.
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CSPUserPrincipal up = (CSPUserPrincipal)request.getUserPrincipal();

        PrintWriter pw = response.getWriter();

        System.out.println(up);
        System.out.println(pw);
        System.out.println(up.getAppUID());

        response.getWriter().write(
                "Username : " + up.getAppUID() + "</br>"
        );

        request.setAttribute("realUID", up.getAppUID());
        request.getServletContext().getRequestDispatcher("/protected")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}