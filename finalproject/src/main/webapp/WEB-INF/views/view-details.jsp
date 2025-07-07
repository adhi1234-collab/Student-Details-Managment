<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Student Details</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

<h2>Student Details</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Register No</th>
        <th>Branch</th>
        <th>CGPA</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.name}</td>
            <td>${student.registerNo}</td>
            <td>${student.branch}</td>
            <td>${student.cgpa}</td>
            <td>
                <a class="button" href="/edit/${student.id}">Edit</a>
                <a class="button" href="/delete/${student.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a class="button" href="/dashboard">Back</a>

</body>
</html>
