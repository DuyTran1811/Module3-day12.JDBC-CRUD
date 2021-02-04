<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <style>
        h1{
            text-align: center;
        }
        h2{
            text-align: center;
        }
    </style>
</head>
<body>
<h1>User Management</h1>
<h2><a href="/users?action=create">Create User</a></h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2> List of Users</h2></caption>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Email</td>
            <td>Country</td>
            <td>Actions</td>
        </tr>
        <c:forEach items="${list}" var="list">
        <tr>
            <td>${list.getId()}</td>
            <td>${list.getName()}</td>
            <td>${list.getEmail()}</td>
            <td>${list.getCountry()}</td>
            <td>
                <a href="/users?action=edit&id=${list.getId()}">Edit</a>
                <a href="/users?action=delete&id=${list.getId()}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
