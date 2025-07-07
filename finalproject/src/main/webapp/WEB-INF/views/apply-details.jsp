<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply Student Details</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

<h2>Apply Student Details</h2>
<form action="/submit-details" method="post">
    Name: <input type="text" name="name" /><br/>
    Register No: <input type="text" name="registerNo" /><br/>
    Branch: <input type="text" name="branch" /><br/>
    CGPA: <input type="number" step="0.01" name="cgpa" /><br/>
    <input type="submit" value="Submit" />
</form>
<a class="button" href="/dashboard">Back</a>

</body>
</html>
