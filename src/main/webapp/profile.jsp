<!-- profile.jsp -->
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h2>User Profile</h2>
    <form action="UpdateProfileServlet" method="post">
        Full Name: <input type="text" name="fullname" value="<%= session.getAttribute("userName") %>" required><br>
        Email: <input type="email" name="email" value="<%= session.getAttribute("email") %>" required><br>
        Mobile: <input type="text" name="mobile" value="<%= session.getAttribute("mobile") %>" required><br>
        Gender: <input type="text" name="gender" value="<%= session.getAttribute("gender") %>" required><br>
        Account Type: <input type="text" name="accountType" value="<%= session.getAttribute("accountType") %>" readonly><br>
        Password: <input type="password" name="password" value="<%= session.getAttribute("password") %>" required><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
