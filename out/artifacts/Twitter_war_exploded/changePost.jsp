<%@ page import="com.Db.DBManager" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.Db.Tweets" %><%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 18.10.2018
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Post</title>
</head>
<body>
<%
    DBManager dbManager = new DBManager();
    dbManager.connect();
    String ids = request.getParameter("id");
    Long id = Long.parseLong(ids);
    Set <Tweets> arr = dbManager.getAllTweets();
    String text="";
    for ( Tweets tw:arr
         ) {
        if (tw.getId().equals(id)){
            text=tw.getTweet();
        }}

%>

<form action="/ChangeTweet?id=<%=id%>" method="post">
    <textarea type="text" name="tweetText"><%=text%></textarea>
    <button type="submit">Send</button> </form>
<br>
<br><br><br>

<form action="/Home" method="get">
    <button type="submit"> Back</button>
</form>
</body>
</html>
