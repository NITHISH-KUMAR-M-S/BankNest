<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<%
    List<String[]> transactions = (List<String[]>) request.getAttribute("transactions");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Transaction History</h2>
        <a href="dashboard.jsp" class="btn btn-secondary mb-3">Back to Dashboard</a>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Amount (₹)</th>
                    <th>Date & Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (transactions != null && !transactions.isEmpty()) {
                        for (String[] row : transactions) {
                %>
                            <tr>
                                <td><%= row[0] %></td>
                                <td><%= row[1] %></td>
                                <td><%= row[2] %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="3" class="text-center">No transactions found.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
