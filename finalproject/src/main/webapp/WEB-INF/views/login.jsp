<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h2>Login</h2>
<form action="/login" method="post">
    Username: <input type="text" name="username" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <input type="submit" value="Login"/>
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<p>Don't have an account? <a href="/register">Go to Register</a></p>
</body>
</html>
