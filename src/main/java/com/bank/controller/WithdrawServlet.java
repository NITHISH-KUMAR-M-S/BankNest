package com.bank.controller;

import com.bank.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String amountStr = request.getParameter("amount");

		try {
			double amount = Double.parseDouble(amountStr);
			double currentBalance = DBInteraction.getBalance(id);

			if (amount <= 0) {
				response.sendRedirect("withdraw.jsp?error=Amount+must+be+greater+than+0");
				return;
			}

			if (amount > currentBalance) {
				response.sendRedirect("withdraw.jsp?error=Insufficient+balance");
				return;
			}

			boolean result = DBInteraction.withdrawAmount(id, amount);
			if (result) {
				DBInteraction.logTransaction(id, "withdraw", amount);
				double updatedBalance = DBInteraction.getBalance(id);
				session.setAttribute("balance", updatedBalance);
				response.sendRedirect("dashboard.jsp?msg=Amount+withdrawn+successfully");
			} else {
				response.sendRedirect("withdraw.jsp?error=Withdraw+failed");
			}

		} catch (NumberFormatException e) {
			response.sendRedirect("withdraw.jsp?error=Invalid+amount+value");
		}
	}
}
