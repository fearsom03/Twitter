<%@ page import="java.util.Set" %>
<%@ page import="com.Db.*" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 18.10.2018
  Time: 8:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile Page</title>
</head>
<body>
<%
    DBManager db = new DBManager();
    db.connect();
    String id1 = request.getParameter("id");
    Long id = Long.parseLong(id1);
    User user = db.getUserByID(id);
    UserDATA userDATA = db.getUserData(user);
%>


        Name : <%=userDATA.getName()%> <br>
        Surname : <%=userDATA.getSurname()%><br>
        Gender : <%= userDATA.getGender()%><br>
        Country : <%=userDATA.getCountry()%><br>
        City : <%=userDATA.getCity()%><br>
        <br>

<%int count=0;
    try {
        Set<Followers> arr = db.getAllFollow();
        System.out.println(arr.size()+"ArraySize");
        for (Followers a:arr) {
            if (user.getId().equals(a.getUserMe().getId())){
                count++;
%>
<%}
}
%>
<br>
Total follow : <%=count%>
<br>
<%
    count=0;
    for (Followers a:arr) {
        if (user.getId().equals(a.getUserToFollow().getId())){
            count++; }
}

}catch (Exception e) {e.printStackTrace();}
%>
<br>
            Total followers : <%=count%><br><br>

            Tweets : <br><br>

<% Set<Tweets> TweetsArray = db.getAllTweets();
    Set<Tweet_answers> TweetsAnsswer = db.getAllComent();
    System.out.println(TweetsArray.size()+"  TweetSize");
    for (Tweets tweet:TweetsArray) {
        if (user.getId().equals(tweet.getUser().getId())){
%>

Post : <%=tweet.getTweet()%> <br>
date : <%=tweet.getPost_date()%>
<br>

<%
    for (Tweet_answers tweet_answers:TweetsAnsswer) {
        if (tweet_answers.getTweets().getId().equals(tweet.getId())){
            UserDATA userDATA1=db.getUserData(tweet_answers.getUser());
            out.println(userDATA1.getName()+" "+userDATA1.getSurname() +"  : "+tweet_answers.getAnswer() +" . "+tweet_answers.getPost_date());
%><br><%
        }
    }
%>




<form action="/AddComent?id=<%=tweet.getId()%>" method="post">
    Comment : <input type="text" name="comment" value="">
    <button type="submit">Send</button>
</form>
<%}
}
%>







    <form action="/Home" method="get">
        <button type="submit"> Back</button>
    </form>

</body>
</html>
