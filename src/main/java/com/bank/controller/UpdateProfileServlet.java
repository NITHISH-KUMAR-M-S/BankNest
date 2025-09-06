package com.bank.controller;

import com.bank.DTO.Bank;
import com.bank.model.DBInteraction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
    	HttpSession hss = request.getSession();
    	int id = (int) hss.getAttribute("id");
        
        // Extract parameters from form
//        int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        long mobile = Long.parseLong(request.getParameter("mobile"));
        String gender = request.getParameter("gender");
        String accountType = request.getParameter("accountType");
        String password = request.getParameter("password");

        // Call model to update data
        boolean isUpdated = DBInteraction.updateUserProfile(id, fullname, email, mobile, gender, accountType, password);

        if (isUpdated == true) {
        	
        	hss.setAttribute("userName", fullname);
            hss.setAttribute("email", email);
            hss.setAttribute("mobile", mobile);
            hss.setAttribute("gender", gender);
            hss.setAttribute("accountType", accountType);
            hss.setAttribute("password", password);
            
            response.sendRedirect("dashboard.jsp?msg=Profile+Updated+Successfully");
        } else {
            response.sendRedirect("dashboard.jsp?error=Profile+Update+Failed");
        }
    }
}



