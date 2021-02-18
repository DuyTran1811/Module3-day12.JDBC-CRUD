<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List SmartPhone</title>
    </head>
    <body>
        <div style="text-align: center">
            <h1>Lis SmartPhone</h1>
            <h2><a href="/smartphones?action=create">Create SmartPhone</a></h2>
            <form action="/smartphones?action=search" method="post">
                <input type="text" name="name" placeholder="Search">
                <input type="submit" value="Search">
            </form>

        </div>
        <div align="center">
            <table border="1">
                <caption><h2>List SmartPhone</h2></caption>
                <tr>
                    <td>ID</td>
                    <td>Brand</td>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Year</td>
                    <td>Size Screen</td>
                    <td>Actions</td>
                </tr>
                <c:forEach items="${list}" var="lists">
                <tr>
                    <td>${lists.getId()}</td>
                    <td>${lists.getBrand()}</td>
                    <td>${lists.getName()}</td>
                    <td>${lists.getPrice()}</td>
                    <td>${lists.getYear()}</td>
                    <td>${lists.getSizescreen()}</td>
                    <td>
                        <a href="/smartphones?action=edit&id=${lists.getId()}">Edit</a>
                        <a href="/smartphones?action=delete&id=${lists.getId()}">Delete</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
