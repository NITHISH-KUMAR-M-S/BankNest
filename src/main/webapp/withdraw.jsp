<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page session="true" %>
<%
    String name = (String) session.getAttribute("userName");
    if (name == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdraw - BankNest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 500px;">
        <h2 class="text-center mb-4">Withdraw Funds</h2>
        <form action="WithdrawServlet" method="post">
            <div class="mb-3">
                <label for="amount" class="form-label">Enter Amount (₹)</label>
                <input type="number" step="0.01" name="amount" id="amount" class="form-control" required min="1">
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-danger">Withdraw</button>
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="dashboard.jsp" class="btn btn-outline-primary">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
    