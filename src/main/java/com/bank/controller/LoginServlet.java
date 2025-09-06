package com.bank.controller;

import com.bank.model.*;
import com.bank.DTO.*;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Bank b = DBInteraction.login(email, password);

		if (b != null) {

			HttpSession hs = request.getSession();
			hs.setAttribute("id", b.getId());
			hs.setAttribute("userName", b.getFullname());
			hs.setAttribute("email", b.getEmail());
			hs.setAttribute("mobile", b.getMobile());
			hs.setAttribute("gender", b.getGender());
			hs.setAttribute("accountType", b.getAccountType());
			hs.setAttribute("password", b.getPassword());
			hs.setAttribute("balance", b.getInitialBalance());

			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("login.jsp?error=Invalid+credentials");
		}
	}
}
