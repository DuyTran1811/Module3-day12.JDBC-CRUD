<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit SmartPhone</title>
    </head>
    <body>
        <div align="center">
            <h1>Edit SmartPhone</h1>
            <form method="post">
                <table border="1">
                    <caption>Add New SmartPhone</caption>
                    <tr>
                        <th>Brand</th>
                        <td><input type="text" name="brand" value="${sp.getBrand()}"></td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td><input type="text" name="name" value="${sp.getName()}"></td>
                    </tr>
                    <tr>
                        <th>Price</th>
                        <td><input type="number" name="price" value="${sp.getPrice()}"></td>
                    </tr>
                    <tr>
                        <th>Year</th>
                        <td><input type="text" name="year" value="${sp.getYear()}"></td>
                    </tr>
                    <tr>
                        <th>SizeScreen</th>
                        <td><input type="text" name="sizescreen" value="${sp.getSizescreen()}"></td>
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
