<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 18.10.2018
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>

            Registration page
            <br>
            <form method="post" action="/Registration">
                Name : <input type="text" name="userName"> <br>
                Surname :<input type="text" name="userSurname"> <br>
                Gender :  <select name="userGender">
                <option value="Male"> Male</option>
                <option value="Female">Female</option>
            </select> <br>
                Country : <input type="text" name="userCount"> <br>
                City :<input type="text" name="userCity"> <br>
                Email :<input type="email" name="userMail"> <br>
                Password <input type="password" name="userPassword"> <br>
                        <button type="submit"> Register </button>
            </form>


<br><br><br><br>
            <form action="/Home" method="get">
                <button type="submit"> Back</button>
            </form>
</body>
</html>
