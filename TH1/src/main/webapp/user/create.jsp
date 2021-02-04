
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Users</title>
    <style>
        h1{
            text-align: center;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Create Users</h1>
<h2>
    <a href="users?action=users">List All Users</a>
</h2>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New User</h2>
            </caption>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>User Email:</th>
                <td>
                    <input type="text" name="email" id="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Country:</th>
                <td>
                    <input type="text" name="country" id="country" size="45"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
