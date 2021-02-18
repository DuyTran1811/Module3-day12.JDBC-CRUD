<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create SmartPhone</title>
</head>
<body>
<div align="center">
    <h1>Create SmartPhone</h1>
    <h2><a href="/smartphones?action=smartphones">List SmartPhone</a></h2>
    <form method="post">
        <table border="1">
            <caption>Add New SmartPhone</caption>
            <tr>
                <th>Brand</th>
                <td><input type="text" name="brand"></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <th>Price</th>
                <td><input type="number" name="price"></td>
            </tr>
            <tr>
                <th>Year</th>
                <td><input type="text" name="year"></td>
            </tr>
            <tr>
                <th>SizeScreen</th>
                <td><input type="text" name="sizescreen"></td>
            </tr>
            <tr>
                <th></th>
                <td colspan="2" align="center"><input type="submit" name="submit"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
