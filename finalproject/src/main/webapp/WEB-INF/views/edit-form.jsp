<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

<h2>Edit Student Record</h2>
<form action="/update" method="post">
    <input type="hidden" name="id" value="${student.id}" />
    Name: <input type="text" name="name" value="${student.name}" /><br/>
    Register No: <input type="text" name="registerNo" value="${student.registerNo}" /><br/>
    Branch: <input type="text" name="branch" value="${student.branch}" /><br/>
    CGPA: <input type="number" step="0.01" name="cgpa" value="${student.cgpa}" /><br/>
    <input type="submit" value="Update" />
</form>
<a class="button" href="/view-details">Cancel</a>

</body>
</html>
