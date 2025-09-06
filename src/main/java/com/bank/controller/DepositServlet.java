package com.bank.controller;

import com.bank.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String amountStr = request.getParameter("amount");

		try {
			double amount = Double.parseDouble(amountStr);

			if (amount <= 0) {
				response.sendRedirect("deposit.jsp?error=Amount+must+be+greater+than+0");
				return;
			}

			boolean result = DBInteraction.depositAmount(id, amount);
			if (result) {
				DBInteraction.logTransaction(id, "deposit", amount);
				double updatedBalance = DBInteraction.getBalance(id);
				session.setAttribute("balance", updatedBalance);
				response.sendRedirect("dashboard.jsp?msg=Amount+deposited+successfully");
			} else {
				response.sendRedirect("deposit.jsp?error=Deposit+failed");
			}

		} catch (NumberFormatException e) {
			response.sendRedirect("deposit.jsp?error=Invalid+amount+value");
		}
	}
}
