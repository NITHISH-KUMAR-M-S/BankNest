package com.bank.controller;

import com.bank.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String mobilestr = request.getParameter("mobile");
		long mobile = Long.parseLong(mobilestr);
		String gender = request.getParameter("gender");
		String accountType = request.getParameter("accountType");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String balanceStr = request.getParameter("balance");
		double initialBalance = 0;

		try {
			initialBalance = Double.parseDouble(balanceStr);
			if (initialBalance < 100) {
				response.sendRedirect("signup.jsp?error=Minimum+initial+balance+should+be+₹100");
				return;
			}
		} catch (NumberFormatException e) {
			response.sendRedirect("signup.jsp?error=Invalid+balance+value");
			return;
		}

		if (!password.equals(confirmPassword)) {
			response.sendRedirect("signup.jsp?error=Passwords+do+not+match");
			return;
		}

		boolean b = DBInteraction.signin(fullname, email, mobile, gender, accountType, password, initialBalance);
		if (b == true) {
			response.sendRedirect("login.jsp?msg=Registered+Successfully");
		} else {
			response.sendRedirect("signup.jsp?error=Registration+Failed");
		}

	}
}
