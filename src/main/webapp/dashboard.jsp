<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%
int userId = (Integer) session.getAttribute("id");
String name = (String) session.getAttribute("userName"); // Ensure it's userName not username
String email = (String) session.getAttribute("email"); // Optional: display balance
long mobile = (Long) session.getAttribute("mobile");
String gender = (String) session.getAttribute("gender");
String accountType = (String) session.getAttribute("accountType");
String password = (String) session.getAttribute("password");
Double balance = (Double) session.getAttribute("balance");
if (name == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard - BankNest</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.card {
	transition: transform 0.3s ease;
}

.card:hover {
	transform: scale(1.05);
}

.navbar-brand {
	font-weight: bold;
	font-size: 1.5rem;
}

body {
	background-color: #f7f9fb;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid px-5">
			<!-- Brand name -->
			<a class="navbar-brand" href="#">BankNest</a>

			<!-- Right side: Welcome, Profile Dropdown, Logout -->
			<div class="d-flex align-items-center">
				<span class="text-white me-4">Welcome, <strong><%=name%></strong></span>

				<!-- Profile Dropdown -->
				<div class="dropdown">
					<button class="btn btn-light dropdown-toggle" type="button"
						data-bs-toggle="dropdown" aria-expanded="false">Profile</button>
					<ul class="dropdown-menu dropdown-menu-end">
						<li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#profileModal">View / Edit Profile</button></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item text-danger" href="logout.jsp">Logout</a></li>
					</ul>
				</div>

			</div>
		</div>
	</nav>



	<!-- Main Dashboard -->
	<div class="container mt-5">
		<div class="text-center mb-5">
			<h2>
				Hello
				<%=name%>👋
			</h2>
			<%
			if (balance != null) {
			%>
			<p class="lead">
				Your Current Balance: ₹<%=String.format("%.2f", balance)%></p>
			<%
			} else {
			%>
			<p class="lead">Welcome to your secure banking dashboard.</p>
			<%
			}
			%>
		</div>

		<div class="row g-4 justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-sm border-0">
					<div class="card-body text-center">
						<h5 class="card-title">💰 Deposit</h5>
						<p class="card-text">Add money to your account securely.</p>
						<a href="deposit.jsp" class="btn btn-success">Deposit Now</a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card shadow-sm border-0">
					<div class="card-body text-center">
						<h5 class="card-title">💸 Withdraw</h5>
						<p class="card-text">Withdraw funds from your account.</p>
						<a href="withdraw.jsp" class="btn btn-danger">Withdraw Now</a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card shadow-sm border-0">
					<div class="card-body text-center">
						<h5 class="card-title">📄 Transactions</h5>
						<p class="card-text">View your transaction history.</p>
						<a href="transactions.jsp" class="btn btn-info">View History</a>
					</div>
				</div>
			</div>
		</div>

		<div class="text-center mt-5">
			<a href="logout.jsp" class="btn btn-outline-secondary">Logout</a>
		</div>
	</div>


	<!-- Profile Modal -->
	<div class="modal fade" id="profileModal" tabindex="-1" aria-labelledby="profileModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="UpdateProfileServlet" method="post">
				<div class="modal-content">
					<div class="modal-header bg-primary text-white">
						<h5 class="modal-title" id="profileModalLabel">Update Profile</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"	aria-label="Close"></button>
					</div>

					<div class="modal-body">
						<div class="mb-3">
							<label class="form-label">User ID</label> 
							<input type="text" class="form-control bg-light" name="id" value="<%=userId%>" readonly>
						</div>

						<div class="mb-3">
							<label class="form-label">Full Name</label> 
							<input type="text" class="form-control" name="name" value="<%=name%>" required>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Email Address</label>
							<input type="email" class="form-control" name="email" value="<%=email%>" required>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Mobile Number</label> 
							<input type="text" class="form-control" name="mobile" value="<%=mobile%>" required>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Gender</label> 
							<select	class="form-select" name="gender" required>
								<option value="Male" <%=gender.equals("Male") ? "selected" : ""%>>Male</option>
								<option value="Female" <%=gender.equals("Female") ? "selected" : ""%>>Female</option>
								<option value="Other" <%=gender.equals("Other") ? "selected" : ""%>>Other</option>
							</select>
						</div>

						<div class="mb-3">
							<label class="form-label">Account Type</label> 
							<select	class="form-select" name="accountType" required>
								<option value="Savings" <%=accountType.equals("Savings") ? "selected" : ""%>>Savings</option>
								<option value="Current"	<%=accountType.equals("Current") ? "selected" : ""%>>Current</option>
							</select>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Change Password</label> 
							<input type="password" class="form-control" name="password" value="<%=password%>" required>
						</div>
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Update</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
