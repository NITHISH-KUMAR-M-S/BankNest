<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up - BankNest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5" style="max-width: 600px;">
        <h2 class="text-center mb-4">Create Your Bank Account</h2>
        <form action="SignupServlet" method="post">
            <div class="mb-3">
                <label for="fullname" class="form-label">Full Name</label>
                <input type="text" name="fullname" id="fullname" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" name="email" id="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="mobile" class="form-label">Mobile Number</label>
                <input type="text" name="mobile" id="mobile" class="form-control" required pattern="[0-9]{10}" title="Enter 10-digit number">
            </div>
            <div class="mb-3">
                <label class="form-label d-block">Gender</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="Male" required>
                    <label class="form-check-label">Male</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="Female">
                    <label class="form-check-label">Female</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="Others">
                    <label class="form-check-label">Others</label>
                </div>
            </div>
            <div class="mb-3">
                <label for="accountType" class="form-label">Account Type</label>
                <select name="accountType" id="accountType" class="form-select" required>
                    <option value="">Select Account Type</option>
                    <option value="Savings">Savings</option>
                    <option value="Current">Current</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" id="password" class="form-control" required minlength="6">
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm Password</label>
                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required minlength="6">
            </div>
            <div class="mb-3">
    			<label for="balance" class="form-label">Initial Balance</label>
    			<input type="number" step="0.01" name="balance" id="balance" class="form-control" required min="0" placeholder="e.g. 1000">
			</div>
            <div class="d-grid">
                <button type="submit" class="btn btn-success">Sign Up</button>
            </div>
        </form>
        <p class="text-center mt-3">
            Already have an account? <a href="login.jsp">Login</a>
        </p>
    </div>
</body>
</html>

