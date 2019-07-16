<%@ page import="com.Db.DBManager" %>
<%@ page import="com.Db.UserDATA" %>
<%@ page import="com.Db.User" %><%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 18.10.2018
  Time: 3:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    DBManager dbManager = new DBManager();
    dbManager.connect();
    String ids = request.getParameter("id");
    Long id = Long.parseLong(ids);
    User user =dbManager.getUserByID(id);
    UserDATA userDATA = dbManager.getUserData(user);
    System.out.println(userDATA.toString());
%>

Change Data :
<form method="post" action="/ChangeData?id=<%=ids%>">
    Name : <input type="text" name="userName" value=<%=userDATA.getName()%>> <br>
    Surname :<input type="text" name="userSurname" value=<%=userDATA.getSurname()%>> <br>
    Gender :  <select name="userGender" value=<%=userDATA.getGender()%>>
    <option value="Male"> Male</option>
    <option value="Female">Female</option>
</select> <br>
    Country : <input type="text" name="userCount" value=<%=userDATA.getCountry()%>> <br>
    City :<input type="text" name="userCity" value=<%=userDATA.getCity()%>> <br>
    Email :<input type="email" name="userMail" value=<%=userDATA.getUser().getEmail()%>> <br>
    <button type="submit"> change </button>
</form>
<br>
Change Password :
<form action="/changePassword?id=<%=ids%>" method="post">
OLD PASSWORD    <input type="password" name="OldPass"><br>
NEW PASSWORD    <input type="password" name="NewPass"><br>
    <button type="submit"> Change Password</button>
</form>
<br>
Delete accaunt :
<form action="/DeleteAcc?id=<%=ids%>" method="post">
     PASSWORD    <input type="password" name="Password"><br>
    <button type="submit">Delete</button>
</form>

<br><br>
<br><br>
<form action="/Home" method="get">
    <button type="submit"> Back</button>
</form>
</body>
</html>
