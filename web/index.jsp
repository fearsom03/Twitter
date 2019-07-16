<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.Db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Twitter v1.0 </title>
</head>

<%
    User user = (User) session.getAttribute("user");
%>

<body>
<%if (user==null){%>
        Welcome page <br>
    <br>

    <form action="/Logining" method="post">
    Login :    <input type="email" name="login"  placeholder="Email"><br>
    Password : <input type="password" name="pass" placeholder="Password"> <br>
    <button type="submit"> Submit</button></form>
    <br>

    <form action="register.jsp" method="post">
        <button type="submit">Register</button>
    </form>
<%}else {%>
   <%DBManager db = new DBManager();
   db.connect();
   UserDATA userDATA=db.getUserData(user);%>

My Profile  <br>
Name : <%=userDATA.getName()%> <br>
Surname : <%=userDATA.getSurname()%> <br>
Gender : <%=userDATA.getGender()%> <br>
Country : <%=userDATA.getCountry()%> <br>
City : <%=userDATA.getCity()%> <br>
<form action="change.jsp?id=<%=user.getId()%>" method="post">
    <button type="submit">Change</button>
</form><br>


Search people :
<form action="/Search" method="post">
    Input name : <input type="text" name="nameOfPeople"><br>
    <button type="submit" >Search</button>
</form>

<%
    try {
        Set<UserDATA> arr = (HashSet<UserDATA>) session.getAttribute("people");
            for (UserDATA a:arr) {
%>
<table >
    <td>
        <%= a.getName()%>
    </td>
    <td>
        <%= a.getSurname()%>
    </td>
    <td>
        <%= a.getCountry()%>
    </td>
    <td>
        <form method="post" action="/Follow?id=<%=a.getUser().getId()%>">
            <button type="submit">follow</button>
        </form>
    </td>
</table>
<%
        }
    }catch (Exception e) {e.printStackTrace();}
%>


<br><br>
List of Follow :
<%int count=0;
    try {
        Set<Followers> arr = (HashSet<Followers>) request.getAttribute("followers");
        System.out.println(arr.size()+"ArraySize");


        for (Followers a:arr) {
        UserDATA otherUserData=db.getUserData(a.getUserToFollow());
        if (user.getId().equals(a.getUserMe().getId())){
            count++;
%>
<br>

<table>
    <td>
        <%=otherUserData.getName() +" "+ otherUserData.getSurname()%>
    </td>
    <td>
        <form method="post" action="otherProfile.jsp?id=<%=otherUserData.getUser().getId()%>">
            <button type="submit">profile</button>
        </form>
    </td>

    <td>
        <form method="post" action="/Unfollow?id=<%=otherUserData.getUser().getId()%>">
            <button type="submit">unfollow</button>
        </form>
    </td>
</table>
<%}
        }
%>
<br><br>
Total follow : <%=count%>
<br>
<br>
List of Followers :
<%
    count=0;
for (Followers a:arr) {
UserDATA otherUserData=db.getUserData(a.getUserMe());
if (user.getId().equals(a.getUserToFollow().getId())){
    count++;
%>
<table >
    <td>
        <%=otherUserData.getName()%>
    </td>

    <%--<td>--%>
        <%--<form method="post" action="/Unfollow?id=<%=otherUserData.getUser().getId()%>">--%>
            <%--<button type="submit">unfollow</button>--%>
        <%--</form>--%>
    <%--</td>--%>
    <td>
        <form method="post" action="otherProfile.jsp?id=<%=otherUserData.getUser().getId()%>">
            <button type="submit">profile</button>
        </form>
    </td>
</table>
<%}
    }

    }catch (Exception e) {e.printStackTrace();}
%>
<br>
Total followers : <%=count%><br><br>

ADD tweet : <form action="/AddTweet" method="post">
        <input type="text" name="tweetText">
        <button type="submit">Send</button>
        </form>


    <% Set <Tweets> TweetsArray = (Set<Tweets>) request.getAttribute("tweets");
        Set<Tweet_answers> TweetsAnsswer = (Set<Tweet_answers>) request.getAttribute("comment");
        System.out.println(TweetsArray.size()+"  TweetSize");
        for (Tweets tweet:TweetsArray) {
            if (user.getId().equals(tweet.getUser().getId())){
            %>

        Post : <%=tweet.getTweet()%> <br>
        date : <%=tweet.getPost_date()%>
<form action="/DeletePost?id=<%=tweet.getId()%>" method="post">
    <button type="submit" >Delete</button>
</form>
<a href="changePost.jsp?id=<%=tweet.getId()%>"> Change </a>
<br><br>

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
    Comment : <input type="text" name="comment"  placeholder="Comment">
    <button type="submit">Send</button>
</form>
<%}
        }
    %>
<form action="/News" method="post">
    <button type="submit">NEWS</button>
</form>











<br><br><br><br><br><br>
<form action="logout" method="post">
    <button type="submit">Logout</button>
</form>


</body>
<%}%>
</html>
