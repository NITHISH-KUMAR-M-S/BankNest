package com.bank.controller;

import com.bank.model.DBInteraction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");

		List<String[]> transactions = DBInteraction.getTransactionHistory(id);
		request.setAttribute("transactions", transactions);
		request.getRequestDispatcher("transactions.jsp").forward(request, response);
	}
}
